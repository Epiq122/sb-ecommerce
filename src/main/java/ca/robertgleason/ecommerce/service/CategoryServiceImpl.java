package ca.robertgleason.ecommerce.service;

import ca.robertgleason.ecommerce.model.Category;
import ca.robertgleason.ecommerce.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    //    private final List<Category> categories = new ArrayList<>();


    private CategoryRepository categoryRepository;


    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    @Override
    public void createCategory(Category category) {
        categoryRepository.save(category);
    }


    @Override
    public void updateCategory(Category category) {
        List<Category> categories = categoryRepository.findAll();
        Category existingCategory = categories.stream()
                .filter(c -> c.getCategoryId().equals(category.getCategoryId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category with id " + category.getCategoryId() + " not found"));

        existingCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(existingCategory);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();

        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "requested category not found"));

        if (category != null) {
            categoryRepository.delete(category);
            return "Category with id " + categoryId + " deleted";
        }

        return "Category not found";
    }

}
