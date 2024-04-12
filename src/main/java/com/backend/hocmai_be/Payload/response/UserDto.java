package com.backend.hocmai_be.Payload.response;

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
    private String email;
    private String avatar;
    private String gender;
    private Date date_of_birth;
    private String phone;
    private Set<Role> roles = new HashSet<>();


}
