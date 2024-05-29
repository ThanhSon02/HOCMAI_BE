package com.backend.hocmai_be;

import com.backend.hocmai_be.Model.Role;
import com.backend.hocmai_be.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class HocmaiBeApplication implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepo;

    public static void main(String[] args) {
        SpringApplication.run(HocmaiBeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        try {
//
//            Role role = new Role();
//            role.setId(0);
//            role.setRoleName("ROLE_ADMIN");
//
//            Role role1 = new Role();
//            role1.setId(1);
//            role1.setRoleName("ROLE_NORMAL");
//
//            List<Role> roles = List.of(role,role1);
//
//            List<Role> result = this.roleRepo.saveAll(roles);
//
//            result.forEach(r -> {
//                System.out.println(r.getRoleName());
//            });
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
