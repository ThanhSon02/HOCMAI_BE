package com.backend.hocmai_be.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class Course {

    @Id
    @Generated
    private Long id;

    @Column
    private String course_name;

    @ManyToOne
    @JoinColumn(name = "categiry_id", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User teacher;

    @Column
    private String price;
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
    @JoinColumn(name = "subject_id",nullable = false)
    private Subject subject;

    @OneToMany(mappedBy = "lesson_name",cascade = CascadeType.ALL)
    private Collection<Lesson> lessons;
}
