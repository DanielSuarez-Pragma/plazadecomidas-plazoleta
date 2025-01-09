package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.PlateListRequest;
import com.plazoleta.application.dto.response.PlateListResponse;
import com.plazoleta.application.dto.request.PlateUpdateRequest;

import java.util.List;

public interface IPlateListHandler {

    void savePlateInList(PlateListRequest plateListRequest);

    List<PlateListResponse> getAllPlatesFromList();

    PlateListResponse getPlateFromList(Long id);

    void updatePlateFromList(PlateUpdateRequest plateUpdateRequest);

    void deletePlateFromList(Long id);
}