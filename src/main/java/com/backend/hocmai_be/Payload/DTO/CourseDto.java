package com.backend.hocmai_be.Payload.DTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseDto {
    private int id;
    private String courseName;

    private Long price;

    private Long salePrice;

    private String description;

    private String imageLink;

    private CategoryDto category;

    private List<ChapterDto> chapters;
}
