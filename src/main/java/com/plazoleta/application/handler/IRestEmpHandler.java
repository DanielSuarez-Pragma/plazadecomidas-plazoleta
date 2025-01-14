package com.plazoleta.application.handler;

import com.plazoleta.application.dto.request.RestEmpRequest;

public interface IRestEmpHandler {

    void saveRestEmp(RestEmpRequest restEmpRequest);
    void deleteRestEmp(RestEmpRequest restEmpRequest);

}
