package com.backend.hocmai_be.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Collection;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @Generated
    private Long id;

    private String category_name;

    @OneToMany(mappedBy = "course_name", cascade = CascadeType.ALL)
    private Collection<Course> courses;
}
