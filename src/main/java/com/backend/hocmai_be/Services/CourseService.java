package com.backend.hocmai_be.Services;

import com.backend.hocmai_be.DTO.request.ChapterReq;
import com.backend.hocmai_be.DTO.request.CourseReq;
import com.backend.hocmai_be.DTO.request.LessonReq;
import com.backend.hocmai_be.Model.Category;
import com.backend.hocmai_be.Model.Chapter;
import com.backend.hocmai_be.Model.Course;
import com.backend.hocmai_be.Model.Lesson;
import com.backend.hocmai_be.DTO.response.CourseRes;
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

    private CourseRes convertToDTO(Course course) {
        CourseRes courseRes = modelMapper.map(course, CourseRes.class);
        return courseRes;
    }
    public List<CourseRes> getAll() {
        List<Course> courses = courseRepository.findAll();
        List<CourseRes> courseResList = courses.stream().map(this::convertToDTO).collect(Collectors.toList());
        return courseResList;
    }

    public void deleteCourse(int courseId) throws Exception{
        courseRepository.deleteById(courseId);
    }

    @Transactional
    public CourseRes save(CourseReq courseReq) {
        try {
            Category category = categoriesRepository.findById(courseReq.getCategoryId()).orElseThrow(() -> new ResolutionException("Không tìm thấy danh mục"));
            Course course = new Course(courseReq.getCourseName(), courseReq.getPrice(), courseReq.getSalePrice(), courseReq.getDescription(), courseReq.getImageLink(), category);
            if(courseReq.getChapters() != null) {
                for (ChapterReq chapterDto : courseReq.getChapters()) {
                    Chapter chapter = new Chapter(chapterDto.getChapterName(), course);
                    if(chapterDto.getLessons() != null) {
                        for (LessonReq lessonReq : chapterDto.getLessons()) {
                            Lesson lesson = new Lesson(lessonReq.getLessonName(), lessonReq.getLessonVideo(), chapter);
                            chapter.getLessons().add(lesson);
                        }
                    }
                    course.getChapters().add(chapter);
                }
            }
            Course courseSaved = courseRepository.save(course);
            CourseRes result = modelMapper.map(courseSaved, CourseRes.class);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<CourseRes> getCourseByCategory(int categoryId) {
        try {
            List<CourseRes> courseResList = courseRepository.getAllCourseByCategory(categoryId).stream().map(this::convertToDTO).collect(Collectors.toList());
            return courseResList;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Transactional
    public CourseRes updateCourse(CourseReq courseReq) {
        try {
            Category category = categoriesRepository.findById(courseReq.getCategoryId()).orElseThrow(() -> new ResolutionException("Không tìm thấy danh mục"));
            Course course = courseRepository.findById(courseReq.getId()).orElseThrow(() -> new ResolutionException("Không tìm thấy khoá học"));
            course.setCourseName(courseReq.getCourseName());
            course.setPrice(courseReq.getPrice());
            course.setDescription(courseReq.getDescription());
            course.setImageLink(courseReq.getImageLink());
            course.setSalePrice(courseReq.getSalePrice());
            course.setCategory(category);
            if(courseReq.getChapters() != null) {
                for (ChapterReq chapterDto : courseReq.getChapters()) {
                    Chapter chapter = new Chapter(chapterDto.getChapterName(), course);
                    if(chapterDto.getLessons() != null) {
                        for (LessonReq lessonReq : chapterDto.getLessons()) {
                            Lesson lesson = new Lesson(lessonReq.getLessonName(), lessonReq.getLessonVideo(), chapter);
                            chapter.getLessons().add(lesson);
                        }
                    }
                    course.getChapters().add(chapter);
                }
            }
            Course courseSaved = courseRepository.save(course);
            CourseRes result = modelMapper.map(courseSaved, CourseRes.class);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
