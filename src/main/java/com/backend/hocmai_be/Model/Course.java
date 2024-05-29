package com.backend.hocmai_be.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String courseName;

    @Column
    private Long price;

    @Column
    private Long salePrice;

    @Column
    private String description;

    @Column
    private String imageLink;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Chapter> chapters = new ArrayList<>();

    public Course(String courseName, Long price, Long salePrice, String description, String imageLink, Category category) {
        this.courseName = courseName;
        this.price = price;
        this.salePrice = salePrice;
        this.description = description;
        this.imageLink = imageLink;
        this.category = category;
    }

    public Course() {

    }
}
