package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.ICategoryServicePort;
import com.plazoleta.domain.exception.NoDataFoundException;
import com.plazoleta.domain.model.Category;
import com.plazoleta.domain.spi.ICategoryPersistencePort;

import java.util.List;

import static com.plazoleta.domain.constants.CategoryConstants.DATA_ERROR;

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
        List<Category> categories = categoryPersistencePort.getAllCategories();
        if (categories.isEmpty()) {
            throw new NoDataFoundException(DATA_ERROR);
        }
        return categoryPersistencePort.getAllCategories();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryPersistencePort.getCategoryById(id);
    }
}
