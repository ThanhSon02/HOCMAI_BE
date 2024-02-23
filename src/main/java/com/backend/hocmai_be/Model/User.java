package com.backend.hocmai_be.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Generated
    private Long id;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String password;

    @Column(length = 15)
    private String phone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    private String avatar;

    @Column(length = 5)
    private String gender;
    private Date date_of_birth;

}
