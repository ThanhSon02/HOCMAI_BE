package com.backend.hocmai_be.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Enrollment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

}
