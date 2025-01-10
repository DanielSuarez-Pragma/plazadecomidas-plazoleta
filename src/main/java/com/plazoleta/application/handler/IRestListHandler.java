package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.RestListRequest;
import com.plazoleta.application.dto.response.RestListResponse;

import java.util.List;

public interface IRestListHandler {

    void saveRestInList(RestListRequest restlistrequest);
    RestListResponse getRestFromList(Long id);
    List<RestListResponse> getAllRestFromList(int page, int size);
    void deleteRestFromList(Long id);
}
