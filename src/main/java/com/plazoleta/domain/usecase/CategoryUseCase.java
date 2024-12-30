package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.ICategoryServicePort;
import com.plazoleta.domain.model.Category;
import com.plazoleta.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryPersistencePort.saveCategory(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryPersistencePort.getAllCategories();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryPersistencePort.getCategoryById(id);
    }
}
