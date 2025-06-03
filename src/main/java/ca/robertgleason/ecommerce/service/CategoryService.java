package ca.robertgleason.ecommerce.service;

import ca.robertgleason.ecommerce.model.Category;
import ca.robertgleason.ecommerce.payload.CategoryDTO;
import ca.robertgleason.ecommerce.payload.CategoryResponse;


public interface CategoryService {


    CategoryResponse getAllCategories();

    CategoryDTO createCategory(CategoryDTO categoryDTO);


    Category updateCategory(Category category, Long categoryId);

    String deleteCategory(Long categoryId);


}
