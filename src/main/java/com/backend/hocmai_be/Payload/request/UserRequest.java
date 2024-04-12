package com.backend.hocmai_be.Payload.request;

import lombok.Data;

import java.util.Date;

@Data
public class UserRequest {
    private String email;
    private String password;
    private String avatar;
    private String gender;
    private String phone;
    private Date dateOfBirth;
}
