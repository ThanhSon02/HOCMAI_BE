package com.backend.hocmai_be.DTO.response;

import lombok.Data;

import java.util.List;

@Data
public class ChapterRes {
    private int id;
    private String chapterName;
    private List<LessonRes> lessons;
}
