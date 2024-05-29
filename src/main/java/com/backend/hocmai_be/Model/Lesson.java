package com.backend.hocmai_be.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Data
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String lessonName;

    @Column
    private String lessonVideoLink;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    @JsonIgnore
    private Chapter chapter;

    public Lesson() {
    }

    public Lesson(String lessonName, String lessonVideoLink, Chapter chapter) {
        this.lessonName = lessonName;
        this.lessonVideoLink = lessonVideoLink;
        this.chapter = chapter;
    }
}
