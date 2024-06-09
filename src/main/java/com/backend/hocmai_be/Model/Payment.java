package com.backend.hocmai_be.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Payment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
}
