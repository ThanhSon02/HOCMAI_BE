package com.backend.hocmai_be.Controllers;
import com.backend.hocmai_be.Model.Category;
import com.backend.hocmai_be.Payload.response.ApiBaseResponse;
import com.backend.hocmai_be.Services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {
    @Autowired
    private CategoriesService categoriesService;
    @GetMapping("/getAll")
    public ResponseEntity<ApiBaseResponse> getAll() {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            List<Category> categoryList = categoriesService.findAll();
            Map<String, Object> map = new HashMap<>();
            map.put("all_category", categoryList);
            response.setData(map);
            response.setSuccess(true);
            response.setStatus(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setSuccess(false);
            response.setMessage(e.getMessage());
            response.setStatus(401);
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ApiBaseResponse> addCategory(@RequestBody String categoryName) {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            Category categorySaved = categoriesService.save(categoryName);
            response.setSuccess(true);
            response.setMessage("Insert new category successfully");
            response.setStatus(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("Insert new category badly");
            response.setStatus(401);
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping
    public ResponseEntity<ApiBaseResponse> getCategoriesByName(@RequestParam(value = "name") String name) {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            List<Category> categories = categoriesService.findByCategoryName(name);
            Map<String, Object> map = new HashMap<>();
            map.put("categories", categories);
            response.setStatus(200);
            response.setData(map);
            response.setSuccess(true);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setStatus(401);
            response.setSuccess(false);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
