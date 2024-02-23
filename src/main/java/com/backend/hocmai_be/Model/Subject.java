package com.backend.hocmai_be.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    private Long id;

    @Column
    private String subject_name;


}
