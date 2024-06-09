package com.backend.hocmai_be.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Collection {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    private String collectionName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "course_collection",joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "collection_id"))
    @JsonManagedReference
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "collection")
    private Set<CollectionMapCourse> collectionMapCourses = new HashSet<>();
}
