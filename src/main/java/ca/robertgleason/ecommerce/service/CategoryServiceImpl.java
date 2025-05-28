package ca.robertgleason.ecommerce.service;

import ca.robertgleason.ecommerce.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;


    @Override
    public List<Category> getAllCategories() {
        return categories;
    }


    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }


    @Override
    public void updateCategory(Category category) {
        Category existingCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(category.getCategoryId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category with id " + category.getCategoryId() + " not found"));

        existingCategory.setCategoryName(category.getCategoryName());
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "requested category not found"));

        if (category != null) {
            categories.remove(category);
            return "Category with id " + categoryId + " deleted";
        }

        return "Category not found";
    }

}
