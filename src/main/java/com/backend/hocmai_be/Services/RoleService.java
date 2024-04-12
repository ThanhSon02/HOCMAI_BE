package com.backend.hocmai_be.Services;

import com.backend.hocmai_be.Model.Role;
import com.backend.hocmai_be.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role findById(int roleId) {
        return roleRepository.findById(roleId).get();
    }
}
