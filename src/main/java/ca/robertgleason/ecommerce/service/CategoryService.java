package ca.robertgleason.ecommerce.service;

import ca.robertgleason.ecommerce.model.Category;

import java.util.List;


public interface CategoryService {


    List<Category> getAllCategories();

    void createCategory(Category category);


    void updateCategory(Category category, Long categoryId);

    String deleteCategory(Long categoryId);


}
