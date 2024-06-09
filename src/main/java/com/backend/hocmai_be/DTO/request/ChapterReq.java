package com.backend.hocmai_be.DTO.request;

import lombok.Data;

import java.util.List;

@Data
public class ChapterReq {
    private String chapterName;
    private List<LessonReq> lessons;
}
