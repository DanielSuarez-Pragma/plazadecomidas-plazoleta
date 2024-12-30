package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Category;
import java.util.List;

public interface ICategoryPersistencePort {

    Category saveCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    void deleteCategoryById(Long id);
}
