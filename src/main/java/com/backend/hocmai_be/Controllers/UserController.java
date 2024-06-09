package com.backend.hocmai_be.Controllers;

import com.backend.hocmai_be.DTO.response.UserRes;
import com.backend.hocmai_be.Model.User;
import com.backend.hocmai_be.DTO.request.ChangePasswordRequest;
import com.backend.hocmai_be.DTO.request.CheckPassRequest;
import com.backend.hocmai_be.DTO.request.UserRequest;
import com.backend.hocmai_be.DTO.response.ApiBaseResponse;
import com.backend.hocmai_be.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/update")
    public ResponseEntity<ApiBaseResponse> updateUser(@RequestBody UserRequest request) {
        ApiBaseResponse response = new ApiBaseResponse();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if(user != null) {
            user.setEmail(request.getEmail());
            user.setPhone(request.getPhone());
            user.setName(request.getName());
            Date dateOfBirth = new Date(request.getDateOfBirth());
            user.setDateOfBirth(dateOfBirth);
            userService.save(user);
            UserRes userResponse = new UserRes(user.getId(),user.getEmail(),user.getAvatar(),user.getGender(),user.getDateOfBirth(), user.getPhone(), user.getName(),user.getRoles());
            response.setMessage("Update successfully");
            response.setStatus(200);
            response.setSuccess(true);
            Map<String, Object> map = new HashMap<>();
            map.put("user", userResponse);
            response.setData(map);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Not found user!");
            response.setStatus(500);
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/password")
    public ResponseEntity<ApiBaseResponse> changePassword(@RequestBody ChangePasswordRequest request) {
        ApiBaseResponse response = new ApiBaseResponse();
        if(!StringUtils.isEmpty(request.getOldPassword()) && !StringUtils.isEmpty(request.getNewPassword())) {
            boolean changePasswordFlag = userService.changPassword(request.getEmail(),request.getOldPassword(), request.getNewPassword());
            if(changePasswordFlag) {
                response.setMessage("Change password successfully!");
                response.setSuccess(true);
                response.setStatus(200);
            } else {
                response.setMessage("Change password failure!");
                response.setSuccess(false);
                response.setStatus(402);
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
        } else {
            response.setMessage("Password is empty!");
            response.setSuccess(false);
            response.setStatus(402);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/check_password")
    public ResponseEntity<ApiBaseResponse> checkPass(@RequestBody CheckPassRequest request) {
        ApiBaseResponse response = new ApiBaseResponse();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        boolean checkPass = userService.checkPass(user.getEmail(), request.getPassword());
        Map<String, Object> map = new HashMap<>();
        if(checkPass) {
            map.put("checkPass", true);
            response.setMessage("Password is correct!");
            response.setSuccess(true);
            response.setStatus(200);
            response.setData(map);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Password is wrong!");
            response.setSuccess(false);
            response.setStatus(401);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/getAllUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> getAllUser() {
        ApiBaseResponse response = new ApiBaseResponse();
        List<UserRes> allUser = userService.getAllUser();
        if (allUser != null) {
            Map<String , Object> map = new HashMap<>();
            map.put("allUser", allUser);
            response.setData(map);
            response.setSuccess(true);
            response.setStatus(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setSuccess(true);
            response.setStatus(200);
            response.setMessage("Không có đữ liệu");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> deleteUser(@PathVariable int id) {
        ApiBaseResponse response = new ApiBaseResponse();
        boolean checkDelete = userService.delete(id);
        if(checkDelete) {
            response.setMessage("Xoá thành công!");
            response.setStatus(200);
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Xoá thất bại!");
            response.setStatus(200);
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
