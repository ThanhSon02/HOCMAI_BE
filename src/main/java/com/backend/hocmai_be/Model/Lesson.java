package com.backend.hocmai_be.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String lesson_name;

    @Column
    private Integer minute;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
