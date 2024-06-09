package com.backend.hocmai_be.Repositories;
import com.backend.hocmai_be.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
//    @Query(value = "SELECT c FROM Category c WHERE c.categoryName like %?1%")
    @Query(value = "SELECT * FROM collection c " +
            "JOIN course_collection cc ON c.id = cc.collection_id " +
            "JOIN course cr ON cc.course_id = cr.id " +
            "WHERE c.collection_name like %?1%", nativeQuery = true)
    List<Category> findCategoriesByName(String categoryName);

}
