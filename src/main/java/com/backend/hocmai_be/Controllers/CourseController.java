package com.backend.hocmai_be.Controllers;

import com.backend.hocmai_be.Payload.DTO.CourseDto;
import com.backend.hocmai_be.Payload.response.ApiBaseResponse;
import com.backend.hocmai_be.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/api/course/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> addCourse(@RequestBody CourseDto courseDto) {
        ApiBaseResponse response = new ApiBaseResponse();
        CourseDto result = courseService.save(courseDto);
        if (result != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("corseSaved", result);
            response.setStatus(200);
            response.setMessage("Đã thêm khoá học mới");
            response.setSuccess(true);
            response.setData(map);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setStatus(400);
            response.setMessage("Đã xảy ra lỗi");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/public/api/course/getAll")
    public ResponseEntity<ApiBaseResponse> getAllCourse() {
        ApiBaseResponse response = new ApiBaseResponse();
        List<CourseDto> dtoList = courseService.getAll();
        Map<String, Object> map = new HashMap<>();
        map.put("allCourse", dtoList);
        response.setSuccess(true);
        response.setStatus(200);
        response.setData(map);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/public/api/course/getAllByCategory")
    public ResponseEntity<ApiBaseResponse> getAllCourseByCategory(@RequestBody int categoryId) {
        ApiBaseResponse response = new ApiBaseResponse();
        List<CourseDto> dtoList = courseService.getCourseByCategory(categoryId);
        Map<String, Object> map = new HashMap<>();
        map.put("allCourseByCategory", dtoList);
        response.setSuccess(true);
        response.setStatus(200);
        response.setData(map);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
