package com.backend.hocmai_be.Controllers;

import com.backend.hocmai_be.Payload.request.UserRequest;
import com.backend.hocmai_be.Payload.response.ApiBaseResponse;
import com.backend.hocmai_be.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @PostMapping("/update")
    public ResponseEntity<ApiBaseResponse> updateUser(@RequestBody UserRequest request, @RequestBody int userId) {
        ApiBaseResponse response = new ApiBaseResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
