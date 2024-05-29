package com.backend.hocmai_be.Services;

import com.backend.hocmai_be.Model.Category;
import com.backend.hocmai_be.Model.Chapter;
import com.backend.hocmai_be.Model.Course;
import com.backend.hocmai_be.Model.Lesson;
import com.backend.hocmai_be.Payload.DTO.ChapterDto;
import com.backend.hocmai_be.Payload.DTO.CourseDto;
import com.backend.hocmai_be.Payload.DTO.LessonDto;
import com.backend.hocmai_be.Repositories.CategoriesRepository;
import com.backend.hocmai_be.Repositories.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;
    private ModelMapper modelMapper = new ModelMapper();

    private CourseDto convertToDTO(Course course) {
        CourseDto courseDTO = modelMapper.map(course, CourseDto.class);
        return courseDTO;
    }
//    private ChapterDto convertToDTO(Chapter chapter) {
//        return modelMapper.map(chapter, ChapterDto.class);
//    }
//
//    private LessonDto convertToDTO(Lesson lesson) {
//        return modelMapper.map(lesson, LessonDto.class);
//    }
    public List<CourseDto> getAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> courseDtoList = courses.stream().map(this::convertToDTO).collect(Collectors.toList());
        return courseDtoList;
    }

    public void deleteCourse(int courseId) {
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public CourseDto save(CourseDto courseDto) {
        try {
            Category category = categoriesRepository.findById(courseDto.getCategory().getId()).orElseThrow(() -> new ResolutionException("Không tìm thấy danh mục"));
            Course course = new Course(courseDto.getCourseName(), courseDto.getPrice(), courseDto.getSalePrice(), courseDto.getDescription(), courseDto.getImageLink(), category);
            for (ChapterDto chapterDto : courseDto.getChapters()) {
                Chapter chapter = new Chapter(chapterDto.getChapterName(), course);
                for (LessonDto lessonDto : chapterDto.getLessons()) {
                    Lesson lesson = new Lesson(lessonDto.getLessonName(), lessonDto.getLessonVideo(), chapter);
                    chapter.getLessons().add(lesson);
                }
                course.getChapters().add(chapter);
            }
            Course courseSaved = courseRepository.save(course);
            CourseDto result = modelMapper.map(courseSaved, CourseDto.class);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public List<CourseDto> getCourseByCategory(int categoryId) {
        try {
            List<CourseDto> courseDtoList = courseRepository.getAllCourseByCategory(categoryId).stream().map(this::convertToDTO).collect(Collectors.toList());
            return courseDtoList;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
