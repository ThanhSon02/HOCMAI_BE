package com.backend.hocmai_be.Services;


import com.backend.hocmai_be.Model.User;
import com.backend.hocmai_be.Payload.response.UserDto;
import com.backend.hocmai_be.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper;
    public UserService() {
        this.modelMapper = new ModelMapper();
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public UserDto registerUser(UserDto userDto, int userId) {
        User user = this.modelMapper.map(userDto, User.class);
        User userSaved = userRepository.save(user);
        UserDto dto = this.modelMapper.map(userSaved, UserDto.class);
        return dto;
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
