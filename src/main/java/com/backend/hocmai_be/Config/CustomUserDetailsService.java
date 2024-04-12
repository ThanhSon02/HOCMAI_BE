package com.backend.hocmai_be.Config;

import com.backend.hocmai_be.Model.User;
import com.backend.hocmai_be.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found" + email));
        return user;
    }
}
