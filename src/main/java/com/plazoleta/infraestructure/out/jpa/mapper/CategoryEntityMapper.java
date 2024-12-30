package com.plazoleta.infraestructure.out.jpa.mapper;

import com.plazoleta.domain.model.Category;
import com.plazoleta.infraestructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CategoryEntityMapper {
    CategoryEntity toCategoryEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
    List<Category> toCategoryList(List<CategoryEntity> categoryEntityList);
}
