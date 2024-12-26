package com.plazoleta.application.handler;

import com.plazoleta.application.dto.RestListRequest;
import com.plazoleta.application.dto.RestListResponse;

import java.util.List;

public interface IRestListHandler {

    void saveRestInList(RestListRequest restlistrequest);
    RestListResponse getRestFromList(Long id);
    List<RestListResponse> getAllRestFromList();
    void deleteRestFromList(Long id);
}
