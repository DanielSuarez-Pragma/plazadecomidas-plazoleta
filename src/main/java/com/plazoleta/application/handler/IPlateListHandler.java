package com.plazoleta.application.handler;

import com.plazoleta.application.dto.PlateListRequest;
import com.plazoleta.application.dto.PlateListResponse;
import com.plazoleta.application.dto.PlateUpdateRequest;

import java.util.List;

public interface IPlateListHandler {

    void savePlateInList(PlateListRequest plateListRequest);

    List<PlateListResponse> getAllPlatesFromList();

    PlateListResponse getPlateFromList(Long id);

    void updatePlateFromList(PlateUpdateRequest plateUpdateRequest);

    void deletePlateFromList(Long id);
}