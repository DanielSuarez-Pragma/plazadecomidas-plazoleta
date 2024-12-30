package com.plazoleta.infraestructure.out.jpa.mapper;

import com.plazoleta.domain.model.Plate;
import com.plazoleta.infraestructure.out.jpa.entity.CategoryEntity;
import com.plazoleta.infraestructure.out.jpa.entity.PlateEntity;
import com.plazoleta.infraestructure.out.jpa.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PlateEntityMapper {

    // Map Plate to PlateEntity
    default PlateEntity toEntity(Plate plate) {
        if (plate == null) {
            return null;
        }
        PlateEntity entity = new PlateEntity();
        entity.setId(plate.getId());
        entity.setName(plate.getName());
        entity.setDescription(plate.getDescription());
        entity.setPrice(plate.getPrice());
        entity.setImageUrl(plate.getImageUrl());
        entity.setActive(plate.getActive());

        // Map category inside plate
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(plate.getCategoryId());
        entity.setCategory(categoryEntity);

        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(plate.getRestaurantId());
        entity.setRestaurant(restaurantEntity);
        return entity;
    }

    // Map PlateEntity to Plate
    default Plate toPlate(PlateEntity plateEntity) {
        if (plateEntity == null) {
            return null;
        }
        return new Plate(
                plateEntity.getId(),
                plateEntity.getName(),
                plateEntity.getCategory().getId(),
                plateEntity.getDescription(),
                plateEntity.getPrice(),
                plateEntity.getRestaurant().getId(),
                plateEntity.getImageUrl(),
                plateEntity.getActive()
        );
    }

    List<Plate> toPlateList(List<PlateEntity> plateEntities);
}