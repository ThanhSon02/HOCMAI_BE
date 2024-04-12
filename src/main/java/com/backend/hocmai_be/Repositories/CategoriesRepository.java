package com.backend.hocmai_be.Repositories;
import com.backend.hocmai_be.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT c FROM Category c WHERE c.category_name like %?1%")
    List<Category> findCategoriesByName(String categoryName);
}
