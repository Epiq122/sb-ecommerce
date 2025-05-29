package ca.robertgleason.ecommerce.repository;


import ca.robertgleason.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
