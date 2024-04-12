package com.backend.hocmai_be.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String subject_name;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Course> courses = new ArrayList<>();
}
