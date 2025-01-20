package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.PlateListRequest;
import com.plazoleta.application.dto.request.PlateStateRequest;
import com.plazoleta.application.dto.response.PlateListResponse;
import com.plazoleta.application.dto.request.PlateUpdateRequest;
import com.plazoleta.application.mapper.request.PlateListRequestMapper;
import com.plazoleta.application.mapper.response.PlateListResponseMapper;

import com.plazoleta.domain.api.ICategoryServicePort;
import com.plazoleta.domain.api.IPlateServicePort;
import com.plazoleta.domain.api.IRestaurantServicePort;
import com.plazoleta.domain.model.Plate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PlateListHandler implements IPlateListHandler {

    private final IPlateServicePort plateServicePort;
    private final ICategoryServicePort categoryServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final PlateListRequestMapper plateListRequestMapper;
    private final PlateListResponseMapper plateListResponseMapper;

    public PlateListHandler(IPlateServicePort plateServicePort, ICategoryServicePort categoryServicePort, IRestaurantServicePort restaurantServicePort, PlateListRequestMapper plateListRequestMapper, PlateListResponseMapper plateListResponseMapper) {
        this.plateServicePort = plateServicePort;
        this.categoryServicePort = categoryServicePort;
        this.restaurantServicePort = restaurantServicePort;
        this.plateListRequestMapper = plateListRequestMapper;
        this.plateListResponseMapper = plateListResponseMapper;
    }

    @Override
    public void savePlateInList(PlateListRequest plateListRequest) {
        Plate plate = plateListRequestMapper.toPlate(plateListRequest);
        plateServicePort.savePlate(plate);
    }

    @Override
    public List<PlateListResponse> getAllPlatesFromList(Long id, int page, int size) {
        return plateListResponseMapper.toResponseList(plateServicePort.getAllPlates(id, page, size));
    }

    @Override
    public PlateListResponse getPlateFromList(Long id) {
            List<Plate> plates = plateServicePort.getAllPlates(0L,0,0);
            return (PlateListResponse) plates.stream().map(plate -> {
                String categoryName = categoryServicePort.getCategoryById(plate.getCategoryId()).getName();
                String restaurantName = restaurantServicePort.getRestaurant(plate.getRestaurantId()).getName();
                return plateListResponseMapper.toResponseDetailed(plate, categoryName, restaurantName);
            }).toList();
        }

    @Override
    public void updatePlateFromList(PlateUpdateRequest plateUpdateRequest) {
        Plate newPlate = plateServicePort.getPlateById(plateUpdateRequest.getId());
        newPlate.setDescription(plateUpdateRequest.getDescription());
        newPlate.setPrice(plateUpdateRequest.getPrice());
        plateServicePort.updatePlate(newPlate);
    }

    @Override
    public void enableDisablePlate(PlateStateRequest plateStateRequest) {
        Plate newPlate = plateServicePort.getPlateById(plateStateRequest.getId());
        newPlate.setActive(plateStateRequest.getActive());
        plateServicePort.enableDisablePlate(newPlate);
    }

    @Override
    public void deletePlateFromList(Long id) {
        plateServicePort.deletePlateById(id);
    }

}