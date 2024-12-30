package com.plazoleta.domain.api;

import com.plazoleta.domain.model.Category;
import java.util.List;

public interface ICategoryServicePort {

    Category saveCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);
}
