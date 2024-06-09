package com.backend.hocmai_be.Controllers;

import com.backend.hocmai_be.DTO.request.CloudinaryDeleteReq;
import com.backend.hocmai_be.DTO.response.ApiBaseResponse;
import com.backend.hocmai_be.Services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class CloudinaryController {
    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/api/cloudinary/upload")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> uploadImage(@RequestParam("image")MultipartFile multipartFile) {
        ApiBaseResponse response = new ApiBaseResponse();
        String data = cloudinaryService.uploadFile(multipartFile);
        if(!StringUtils.isEmpty(data)) {
            Map<String, Object> map = new HashMap<>();
            map.put("imageLink", data);
            response.setSuccess(true);
            response.setStatus(200);
            response.setData(map);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setSuccess(false);
            response.setStatus(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/api/cloudinary/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> deleteImage(@RequestBody CloudinaryDeleteReq imageLink) {
        ApiBaseResponse response = new ApiBaseResponse();
        String publicId = imageLink.getImageLink().split("/")[7].split("\\.")[0];
        boolean checkResult = cloudinaryService.deleteFile(publicId);
        if(checkResult) {
            response.setSuccess(true);
            response.setStatus(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setSuccess(false);
            response.setStatus(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
