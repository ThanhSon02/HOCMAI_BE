package com.backend.hocmai_be.DTO.request;

import lombok.Data;

import java.util.List;

@Data
public class CourseReq {
    private int id;

    private String courseName;

    private Long price;

    private Long salePrice;

    private String description;

    private String imageLink;

    private int categoryId;

    private List<ChapterReq> chapters;
}
