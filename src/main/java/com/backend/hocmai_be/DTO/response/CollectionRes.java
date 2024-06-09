package com.backend.hocmai_be.DTO.response;

import lombok.Data;

import java.util.Set;

@Data
public class CollectionRes {
    private int id;
    private String collectionName;
    private Set<CourseRes> courses;
}
