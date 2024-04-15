package com.backend.hocmai_be.Controllers;

import com.backend.hocmai_be.Config.CustomUserDetailsService;
import com.backend.hocmai_be.Model.Role;
import com.backend.hocmai_be.Model.User;
import com.backend.hocmai_be.Payload.request.LoginRequest;
import com.backend.hocmai_be.Payload.request.UserRequest;
import com.backend.hocmai_be.Payload.response.ApiBaseResponse;
import com.backend.hocmai_be.Payload.response.JwtAuthResponse;
import com.backend.hocmai_be.Payload.response.UserDto;
import com.backend.hocmai_be.Services.RoleService;
import com.backend.hocmai_be.Services.UserService;
import com.backend.hocmai_be.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private RoleService roleService;
    @PostMapping("/register")
    public ResponseEntity<ApiBaseResponse> register(@RequestBody UserRequest registerRequest) {
        ApiBaseResponse response = new ApiBaseResponse();
        if (Boolean.TRUE.equals(userService.existsUserByEmail(registerRequest.getEmail()))) {
            response.setSuccess(false);
            response.setMessage("Email is already taken");
            response.setStatus(401);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        String email = registerRequest.getEmail().toLowerCase();
        String passwordEncode = passwordEncoder.encode(registerRequest.getPassword());
        String phone = registerRequest.getPhone();
        String avatar = registerRequest.getAvatar();
        String gender = registerRequest.getGender();
        String firstName = registerRequest.getFirstName();
        String lastName = registerRequest.getLastName();
        Role role = roleService.findById(3);
        Date dateOfBirth = registerRequest.getDateOfBirth();
        User user = new User(email, passwordEncode, phone, avatar, gender, dateOfBirth, firstName, lastName);
        user.getRoles().add(role);
        User result = userService.save(user);
        response.setSuccess(true);
        response.setMessage("User resgistered successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiBaseResponse> login(@RequestBody LoginRequest loginRequest) {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            response.setData(null);
            response.setMessage(e.getMessage());
            response.setSuccess(false);
            response.setStatus(401);
//            throw new ApiException("fenwif");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        User userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        String jwt = jwtUtil.generateToken(userDetails);
        UserDto userResponse = new UserDto(userDetails.getEmail(),userDetails.getPhone(),userDetails.getGender(),userDetails.getDateOfBirth(), userDetails.getAvatar(), userDetails.getFirstName(), userDetails.getLastName(),userDetails.getRoles());
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(jwt);
        Map<String, Object> map = new HashMap<>();
        map.put("token", jwtAuthResponse.getToken());
        map.put("user", userResponse);
        response.setData(map);
        response.setMessage("Login successfully");
        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
