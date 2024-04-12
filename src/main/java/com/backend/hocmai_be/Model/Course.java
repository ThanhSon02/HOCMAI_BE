package com.backend.hocmai_be.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String course_name;

    @Column
    private Long price;

    @Column
    private Date begin_time;

    @Column
    private Date end_time;

    @Column
    private String description;

    @Column
    private String image_link;

    @Column
    private String video_link;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Subject subject;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lesson> lessons = new ArrayList<>();
}
