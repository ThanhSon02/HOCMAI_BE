package com.backend.hocmai_be.DTO.request;

import lombok.Data;

import java.util.Set;

@Data
public class CollectionReq {
    private String collectionName;
    private Set<Integer> courseIds;
}
