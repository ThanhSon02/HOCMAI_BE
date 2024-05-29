package com.backend.hocmai_be.Services;

import com.backend.hocmai_be.Model.Chapter;
import com.backend.hocmai_be.Repositories.ChapterReposity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
public class ChapterService {
    private ChapterReposity chapterReposity;

    private ModelMapper modelMapper;
    public Chapter save(Chapter chapter) {
        try {
            return chapterReposity.save(chapter);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Chapter updateChapter(Chapter chapter) {
        try {
            Chapter chapter1 = chapterReposity.findById(chapter.getId()).orElseThrow(() -> new ResolutionException("Không tìm thấy chương"));
            chapter1.setChapterName(chapter.getChapterName());
            chapter1.setLessons(chapter.getLessons());
            return chapterReposity.save(chapter1);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
