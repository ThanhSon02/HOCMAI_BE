package com.backend.hocmai_be.Services;

import com.backend.hocmai_be.Model.Course;
import com.backend.hocmai_be.Repositories.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    public void deleteCourse(int courseId) {
        courseRepository.deleteById(courseId);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }
}
