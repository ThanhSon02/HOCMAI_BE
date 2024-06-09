package com.backend.hocmai_be.Model;

import com.backend.hocmai_be.DTO.response.CourseRes;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Coupon {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    private String couponName;

    private String couponCode;

    private int amount;

    @OneToMany
    private List<Course> courses;

    private Date createAt;

    private Date timeExpired;
}
