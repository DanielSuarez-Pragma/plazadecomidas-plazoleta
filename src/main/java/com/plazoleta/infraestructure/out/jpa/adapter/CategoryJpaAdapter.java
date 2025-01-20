package com.plazoleta.infraestructure.out.jpa.adapter;

import com.plazoleta.domain.model.Category;
import com.plazoleta.domain.spi.ICategoryPersistencePort;
import com.plazoleta.infraestructure.out.jpa.entity.CategoryEntity;
import com.plazoleta.infraestructure.out.jpa.mapper.CategoryEntityMapper;
import com.plazoleta.infraestructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {
    private final ICategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public Category saveCategory(Category category) {
        return categoryEntityMapper.toCategory(categoryRepository.save(categoryEntityMapper.toCategoryEntity(category)));
    }

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return categoryEntityMapper.toCategoryList(categoryEntities);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryEntityMapper.toCategory(categoryRepository.findCategoryEntityById(id));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
