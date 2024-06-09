package com.backend.hocmai_be.Model;

import jakarta.persistence.*;
import jdk.jfr.Registered;
import lombok.Data;

@Entity
@Data
public class CollectionMapCourse {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
