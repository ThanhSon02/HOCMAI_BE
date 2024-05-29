package com.backend.hocmai_be.Payload.DTO;

import lombok.Data;

import java.util.List;

@Data
public class ChapterDto {
    private int id;
    private String chapterName;
    private List<LessonDto> lessons;
}
