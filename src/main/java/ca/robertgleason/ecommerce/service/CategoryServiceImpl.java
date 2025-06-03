package ca.robertgleason.ecommerce.service;

import ca.robertgleason.ecommerce.exceptions.APIException;
import ca.robertgleason.ecommerce.exceptions.ResourceNotFoundException;
import ca.robertgleason.ecommerce.model.Category;
import ca.robertgleason.ecommerce.payload.CategoryDTO;
import ca.robertgleason.ecommerce.payload.CategoryResponse;
import ca.robertgleason.ecommerce.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    private ModelMapper modelMapper;


    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponse getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new APIException("No categories found");
        }
        List<CategoryDTO> categoryDTOS = categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).toList();
        return new CategoryResponse(categoryDTOS);
    }


    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategoryFromDB = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategoryFromDB != null) {
            throw new APIException("Category with name " + category.getCategoryName() + " already exists");
        }
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }


    @Override
    public Category updateCategory(Category category, Long categoryId) {
        Optional<Category> savedCategoryOptional = categoryRepository.findById(categoryId);
        Category savedCategory = savedCategoryOptional.orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId + ""));

        savedCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(savedCategory);
        return savedCategory;
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId + ""));
        if (category != null) {
            categoryRepository.delete(category);
            return "Category with id " + categoryId + " deleted";
        }

        return "Category not found";
    }

}
