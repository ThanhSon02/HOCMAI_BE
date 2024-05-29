package com.backend.hocmai_be.Repositories;

import com.backend.hocmai_be.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("select n from Course n where n.category.id = ?1")
    List<Course> getAllCourseByCategory(int categoryId);
}
