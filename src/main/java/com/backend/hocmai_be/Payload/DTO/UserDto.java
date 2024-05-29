package com.backend.hocmai_be.Payload.DTO;

import com.backend.hocmai_be.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;
    private String email;
    private String avatar;
    private String gender;
    private Date dateOfBirth;
    private String phone;
    private String name;
    private Set<Role> roles = new HashSet<>();
}
