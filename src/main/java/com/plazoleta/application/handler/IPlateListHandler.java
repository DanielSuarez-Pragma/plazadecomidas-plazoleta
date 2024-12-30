package com.plazoleta.application.handler;

import com.plazoleta.application.dto.PlateListRequest;
import com.plazoleta.application.dto.PlateListResponse;

import java.util.List;

public interface IPlateListHandler {

    void savePlateInList(PlateListRequest plateListRequest);

    List<PlateListResponse> getAllPlatesFromList();

    PlateListResponse getPlateFromList(Long id);

    void deletePlateFromList(Long id);
}