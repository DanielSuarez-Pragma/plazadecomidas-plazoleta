package com.plazoleta.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestEmpRequest {

    private Long restaurantId;
    private List<Long> employeeIds;

}
