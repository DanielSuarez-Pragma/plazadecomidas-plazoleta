package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.exception.InvalidErrorException;
import com.plazoleta.domain.exception.NoDataFoundException;
import com.plazoleta.domain.model.*;
import com.plazoleta.domain.spi.*;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import static com.plazoleta.domain.constants.ErrorOrderConstants.*;
import static com.plazoleta.domain.constants.ErrorRestConstants.*;
import static com.plazoleta.domain.constants.OrderConstants.PHONE_NUMBER;
import static com.plazoleta.domain.constants.OrderStatusConstants.*;

@AllArgsConstructor
public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserPersistencePort userPersistencePort;
    private final IPlatePersistencePort platePersistencePort;
    private final IOrderPlatePersistencePort orderPlatePersistencePort;
    private final IRestEmpPersistencePort restEmpPersistencePort;
    private final IOrderPinPersistencePort orderPinPersistencePort;
    private  final ITwilioFeignClientPort twilioFeignClientPort;
    private final ITraceFeignPersistencePort traceFeignPersistencePort;

    @Override
    public void saveOrder(Order order, List<OrderPlate> orderPlateList) {

        if(restaurantPersistencePort.getRestaurant(order.getRestaurantId()) == null) {
            throw new InvalidErrorException(NO_REST_FOUNDS);
        }
        if (getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), PENDING) != null  ||
                getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), IN_PREPARATION) != null ||
                getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), READY) != null){
                throw new InvalidErrorException(ORDER_EXIST);
            }

        orderPlateList.forEach(orderPlate -> {
            if (!Objects.equals(platePersistencePort.getPlateById(orderPlate.getPlateId()).getRestaurantId(), order.getRestaurantId())) {
                throw new InvalidErrorException(REST_ID_MISMATCH);
            }
            if (Boolean.FALSE.equals(platePersistencePort.getPlateById(orderPlate.getPlateId()).getActive())){
                throw new InvalidErrorException(PLATE_NOT_ACTIVE);
            }
        });

        order.setDate(new Date());

        order.setStatus(PENDING);

        order.setClientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()));

        traceFeignPersistencePort.saveTrace(generateTrace(order, NO_EXIST));

        orderPersistencePort.saveOrder(order);

        Order orderSaved = getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), PENDING);

        orderPlateList.forEach(orderPlate -> {
            orderPlate.setOrderId(orderSaved.getId());
            orderPlatePersistencePort.saveOrderPlate(orderPlate);
        });

    }

    @Override
    public Order getOrderByclientId(Long clientId, String status) {
        return orderPersistencePort.getOrderByClientId(clientId, status);
    }

    @Override
    public List<Order> getAllPlates(Long restaurantId, String status, Integer size, Integer page) {
        if(restaurantPersistencePort.getRestaurant(restaurantId) == null) {
            throw new InvalidErrorException(NO_REST_FOUNDS);
        }
        if (restEmpPersistencePort.findByRestaurantIdAndEmployeeId(restaurantId, userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId())) == null) {
            throw new InvalidErrorException(NO_OWNER_REST);
        }
        List<Order> orders = orderPersistencePort.findByRestaurantIdAndStatus(restaurantId, status, size, page);
        if (orders.isEmpty()) {
            throw new NoDataFoundException(NO_DATA_FOUND);
        }

        return orders;
    }

    @Override
    public void takeOrder(Long id) {
        Order order = orderPersistencePort.findById(id);
        if (restEmpPersistencePort.findByRestaurantIdAndEmployeeId(order.getRestaurantId(), userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId())) == null) {
            throw new InvalidErrorException(NO_OWNER_REST);
        }
        if(!order.getStatus().equals(PENDING)){
            throw new InvalidErrorException(ORDER_NOT_PENDING);
        }
        if(order.getChefId() != null){
            throw new InvalidErrorException(ORDER_HAS_CHEF);
        }
        order.setStatus(IN_PREPARATION);
        order.setChefId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()));
        traceFeignPersistencePort.saveTrace(generateTrace(order, PENDING));
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public void notifyOrder(Long id) {
        Order order = orderPersistencePort.findById(id);
        if (!Objects.equals(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), order.getChefId())) {
            throw new InvalidErrorException(NO_OWNER_REST);
        }
        if(!order.getStatus().equals(IN_PREPARATION)){
            throw new InvalidErrorException(ORDER_NOT_IN_PREPARATION);
        }
        order.setStatus(READY);
        traceFeignPersistencePort.saveTrace(generateTrace(order, IN_PREPARATION));
        orderPersistencePort.saveOrder(order);
        OrderPin orderPin = new OrderPin();
        orderPin.setOrderId(id);
        orderPin.setOrderId(order.getId());
        orderPin.setPin(generatePin());
        orderPinPersistencePort.saveOrderPin(orderPin);
        String message = "Buen día, señor(a), su pedido ya está listo para recoger.\nRecuerda mostrar el siguiente pin " + orderPin.getPin() + " para poder entregar tu pedido.";
        String phoneNumber = PHONE_NUMBER;
        MessageModel messageModel = new MessageModel(phoneNumber, message);
        twilioFeignClientPort.sendMessage(messageModel);
    }

    @Override
    public void deliverOrder(Long orderID, String pin) {
        Order order = orderPersistencePort.findById(orderID);
        if (!Objects.equals(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), order.getChefId())) {
            throw new InvalidErrorException(NO_OWNER_REST);
        }
        if(!order.getStatus().equals(READY)){
            throw new InvalidErrorException(ORDER_NOT_READY);
        }
        if(orderPinPersistencePort.getOrderPinByOrderIdAndPin(order.getId(), pin) == null){
            throw new InvalidErrorException(ORDER_NOT_PIN);
        }
        orderPinPersistencePort.deleteOrderPin(orderPinPersistencePort.getOrderPinByOrderIdAndPin(order.getId(), pin) );
        order.setStatus(DELIVERED);
        traceFeignPersistencePort.saveTrace(generateTrace(order, READY));
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public CancelMessage cancelOrder(Long id) {
        Order order = orderPersistencePort.findById(id);
        if (!Objects.equals(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), order.getClientId())) {
            throw new InvalidErrorException(NO_OWNER_REST);
        }
        if(order.getStatus().equals(CANCELLED)){
            return new CancelMessage(ORDER_NOT_CANCELLED);
        }
        if(!order.getStatus().equals(PENDING)){
            return new CancelMessage(ORDER_NOT_PENDING_OR_CANCELLED);
        }
        order.setStatus(CANCELLED);
        traceFeignPersistencePort.saveTrace(generateTrace(order, PENDING));
        orderPersistencePort.saveOrder(order);
        return new CancelMessage(ORDER_CANCELLED);
    }

    @Override
    public List<TraceClient> getTraceClient() {
        List<TraceClient> traceClients = traceFeignPersistencePort.findTraceClient(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()));
        if(traceClients == null){
            throw new NoDataFoundException(NO_DATA_FOUND);
        }
        return traceClients;
    }

    @Override
    public List<TraceClient> getTraceRestaurant(Long restaurantId) {
        if (!Objects.equals(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), restaurantPersistencePort.getRestaurant(restaurantId).getOwnerId())) {
            throw new InvalidErrorException(NO_OWNER_REST);
        }
        List<TraceClient> traceRestaurant = traceFeignPersistencePort.findTraceRestaurant(restaurantId);
        if(traceRestaurant == null){
            throw new NoDataFoundException(NO_DATA_FOUND);
        }
        return traceRestaurant;
    }

    private String generatePin(){
        Random random = new Random();
        List<OrderPin> orderPinList = orderPinPersistencePort.getAllOrderPin();
        int pin = 1000 + random.nextInt(9000);
        AtomicReference<String> pinString = new AtomicReference<>(Integer.toString(pin));
        orderPinList.forEach(orderPin -> {
            if (orderPin.getPin().equals(pin)) {
                int newPin = 1000 + random.nextInt(9000);
                pinString.set(Integer.toString(newPin));
            }
        });
        return pinString.get();
    }

    private TraceModel generateTrace(Order order, String lastState){
        TraceModel traceModel = new TraceModel();
        traceModel.setOrderId(order.getId());
        traceModel.setClientId(order.getClientId());
        traceModel.setClientEmail(userPersistencePort.getEmailByUserId(order.getClientId()));
        traceModel.setDate(new Date());
        traceModel.setLastState(lastState);
        traceModel.setNewState(order.getStatus());
        traceModel.setEmployeeId(order.getChefId());
        traceModel.setEmployeeEmail(userPersistencePort.getEmailByUserId(order.getChefId()));
        traceModel.setRestaurantId(order.getRestaurantId());
        return traceModel;
    }
}