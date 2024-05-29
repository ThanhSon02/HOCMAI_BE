package com.backend.hocmai_be.Services;

import com.backend.hocmai_be.Model.Lesson;
import com.backend.hocmai_be.Repositories.LessonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    private LessonRepository lessonRepository;
    private ModelMapper modelMapper;
    public Lesson save(Lesson lesson) {
        try {
            return lessonRepository.save(lesson);
        }catch (Exception e) {
            return null;
        }
    }
}
