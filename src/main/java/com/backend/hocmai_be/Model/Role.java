package com.backend.hocmai_be.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 20)
    private String role_name;

}
