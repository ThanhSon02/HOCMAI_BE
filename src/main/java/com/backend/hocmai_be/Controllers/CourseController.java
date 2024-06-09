package com.backend.hocmai_be.Controllers;

import com.backend.hocmai_be.DTO.request.CourseReq;
import com.backend.hocmai_be.DTO.response.CourseRes;
import com.backend.hocmai_be.DTO.response.ApiBaseResponse;
import com.backend.hocmai_be.Services.CloudinaryService;
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

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/api/course/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> addCourse(@RequestBody CourseReq courseReq) {
        ApiBaseResponse response = new ApiBaseResponse();
        CourseRes result = courseService.save(courseReq);
        if (result != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("courseSaved", result);
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

    @DeleteMapping("/api/course/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> deleteCourse(@RequestParam int courseId) {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            courseService.deleteCourse(courseId);
            Map map = new HashMap();
            map.put("courseDeleted", courseId);
            response.setStatus(200);
            response.setMessage("Đã xoá khoá học");
            response.setSuccess(true);
            response.setData(map);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(500);
            response.setMessage("Khoá học không tồn tại");
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/course/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> updateCourse(@RequestBody CourseReq request) {
        ApiBaseResponse response = new ApiBaseResponse();
        CourseRes courseRes = courseService.updateCourse(request);
        if (courseRes != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("courseUpdated", courseRes);
            response.setStatus(200);
            response.setMessage("Khoá học đã được cập nhật");
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
        List<CourseRes> dtoList = courseService.getAll();
        Map<String, Object> map = new HashMap<>();
        map.put("allCourse", dtoList);
        response.setSuccess(true);
        response.setStatus(200);
        response.setData(map);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/public/api/course/getAllByCategory")
    public ResponseEntity<ApiBaseResponse> getAllCourseByCategory(@RequestParam(name = "categoryId") int categoryId) {
        ApiBaseResponse response = new ApiBaseResponse();
        List<CourseRes> dtoList = courseService.getCourseByCategory(categoryId);
        Map<String, Object> map = new HashMap<>();
        map.put("allCourseByCategory", dtoList);
        response.setSuccess(true);
        response.setStatus(200);
        response.setData(map);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
