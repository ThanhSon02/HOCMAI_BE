package com.backend.hocmai_be.DTO.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}