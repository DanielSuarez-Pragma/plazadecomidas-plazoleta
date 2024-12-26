package com.plazoleta.application.mapper;

import com.plazoleta.application.dto.RestListRequest;
import com.plazoleta.dominio.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface RestListRequestMapper {
    //@Mapping(target = "id", ignore = true)
    Restaurant toRestaurant(RestListRequest restListRequest);

}
