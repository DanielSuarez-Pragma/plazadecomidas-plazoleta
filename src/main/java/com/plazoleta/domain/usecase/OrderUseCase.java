package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IOrderServicePort;
import com.plazoleta.domain.model.MessageModel;
import com.plazoleta.domain.model.Order;
import com.plazoleta.domain.model.OrderPin;
import com.plazoleta.domain.model.OrderPlate;
import com.plazoleta.domain.spi.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

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

    @Override
    public void saveOrder(Order order, List<OrderPlate> orderPlateList) {

        if(restaurantPersistencePort.getRestaurant(order.getRestaurantId()) == null) {
            throw new IllegalArgumentException("Restaurant not found");
        }
        if (getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), PENDING) != null  ||
                getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), IN_PREPARATION) != null ||
                getOrderByclientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), READY) != null){
                throw new IllegalArgumentException("Order already exists");
            }

        orderPlateList.forEach(orderPlate -> {
            if (!Objects.equals(platePersistencePort.getPlateById(orderPlate.getPlateId()).getRestaurantId(), order.getRestaurantId())) {
                throw new IllegalArgumentException("Restaurant id mismatch");
            }
            if (Boolean.FALSE.equals(platePersistencePort.getPlateById(orderPlate.getPlateId()).getActive())){
                throw new IllegalArgumentException("Plate is not active");
            }
        });

        order.setClientId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()));

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
            throw new IllegalArgumentException("Restaurant not found");
        }
        if (restEmpPersistencePort.findByRestaurantIdAndEmployeeId(restaurantId, userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId())) == null) {
            throw new IllegalArgumentException("You dont have permission to access this resource");
        }

        return orderPersistencePort.findByRestaurantIdAndStatus(restaurantId, status, size, page);
    }

    @Override
    public void takeOrder(Long id) {
        Order order = orderPersistencePort.findById(id);
        if (restEmpPersistencePort.findByRestaurantIdAndEmployeeId(order.getRestaurantId(), userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId())) == null) {
            throw new IllegalArgumentException("You dont have permission to access this resource");
        }
        if(!order.getStatus().equals(PENDING)){
            throw new IllegalArgumentException("Order is not Pendiente");
        }
        if(order.getChefId() != null){
            throw new IllegalArgumentException("The order has chef");
        }
        order.setStatus(IN_PREPARATION);
        order.setChefId(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()));
        orderPersistencePort.saveOrder(order);
    }

    @Override
    public void notifyOrder(Long id) {
        Order order = orderPersistencePort.findById(id);
        if (!Objects.equals(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), order.getChefId())) {
            throw new IllegalArgumentException("You dont have permission to access this resource");
        }
        if(!order.getStatus().equals(IN_PREPARATION)){
            throw new IllegalArgumentException("Order is not in preparation");
        }
        order.setStatus(READY);
        orderPersistencePort.saveOrder(order);
        OrderPin orderPin = new OrderPin();
        orderPin.setOrderId(id);
        orderPin.setOrderId(order.getId());
        orderPin.setPin(generatePin());
        orderPinPersistencePort.saveOrderPin(orderPin);
        String message = "Buen día, señor(a), su pedido ya está listo para recoger.\nRecuerda mostrar el siguiente pin " + orderPin.getPin() + " para poder entregar tu pedido.";
        String phoneNumber = "+573229350406";

        MessageModel messageModel = new MessageModel(phoneNumber, message);

        twilioFeignClientPort.sendMessage(messageModel);
    }

    @Override
    public void deliverOrder(Long orderID, String pin) {
        Order order = orderPersistencePort.findById(orderID);
        if (!Objects.equals(userPersistencePort.getUserByEmail(userPersistencePort.getAuthenticatedUserId()), order.getChefId())) {
            throw new IllegalArgumentException("You dont have permission to access this resource");
        }
        if(!order.getStatus().equals(READY)){
            throw new IllegalArgumentException("Order is not in ready state");
        }
        if(orderPinPersistencePort.getOrderPinByOrderIdAndPin(order.getId(), pin) == null){
            throw new IllegalArgumentException("The pin dont exist");
        }
        orderPinPersistencePort.deleteOrderPin(orderPinPersistencePort.getOrderPinByOrderIdAndPin(order.getId(), pin) );
        order.setStatus(DELIVERED);
        orderPersistencePort.saveOrder(order);
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
}
