package com.plazoleta.infraestructure.out.jpa.mapper;

import com.plazoleta.domain.model.OrderPlate;
import com.plazoleta.infraestructure.out.jpa.entity.OrderPlateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderPlateEntityMapper {

    default OrderPlateEntity toEntity(OrderPlate orderPlate){
        if(orderPlate == null) {
            return null;
        }
        OrderPlateEntity orderPlateEntity = new OrderPlateEntity();
        orderPlateEntity.setId(orderPlateEntity.getId());
        orderPlateEntity.setOrderId(orderPlate.getOrderId());
        orderPlateEntity.setPlateId(orderPlate.getPlateId());
        orderPlateEntity.setQuantity(orderPlate.getQuantity());
        return orderPlateEntity;
    }

}
