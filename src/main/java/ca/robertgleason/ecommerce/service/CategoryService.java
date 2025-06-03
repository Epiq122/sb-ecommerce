package ca.robertgleason.ecommerce.service;

import ca.robertgleason.ecommerce.payload.CategoryDTO;
import ca.robertgleason.ecommerce.payload.CategoryResponse;


public interface CategoryService {


    CategoryResponse getAllCategories();

    CategoryDTO createCategory(CategoryDTO categoryDTO);


    CategoryDTO updateCategory(CategoryDTO categoryDto, Long categoryId);

    String deleteCategory(Long categoryId);


}
