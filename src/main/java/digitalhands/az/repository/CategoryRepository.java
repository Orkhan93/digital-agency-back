package digitalhands.az.repository;

import digitalhands.az.entity.Category;
import digitalhands.az.wrapper.CategoryWrapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<CategoryWrapper> getAllCategories();

}