package com.backend.hocmai_be.DTO.response;
import lombok.Data;

import java.util.List;

@Data
public class CourseRes {
    private int id;
    private String courseName;

    private Long price;

    private Long salePrice;

    private String description;

    private String imageLink;

    private CategoryRes category;

    private List<ChapterRes> chapters;
}
