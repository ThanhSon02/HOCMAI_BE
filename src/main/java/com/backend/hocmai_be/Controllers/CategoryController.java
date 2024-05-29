package com.backend.hocmai_be.Controllers;
import com.backend.hocmai_be.Model.Category;
import com.backend.hocmai_be.Payload.DTO.CategoryDto;
import com.backend.hocmai_be.Payload.response.ApiBaseResponse;
import com.backend.hocmai_be.Services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class CategoryController {
    @Autowired
    private CategoriesService categoriesService;
    @GetMapping("/public/api/category/getAll")
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

    @PostMapping("/api/category/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> addCategory(@RequestBody CategoryDto categoryDto) {
        ApiBaseResponse response = new ApiBaseResponse();
        try {
            if(!StringUtils.isEmpty(categoryDto.getCategoryName())) {
                Category category = categoriesService.save(categoryDto.getCategoryName());
                Map<String, Object> map = new HashMap<>();
                map.put("category", category);
                response.setData(map);
                response.setSuccess(true);
                response.setMessage("Insert new category successfully");
                response.setStatus(200);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setMessage("Category name is empty!");
                response.setSuccess(false);
                response.setStatus(200);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
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

    @PostMapping("/api/category/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> updateCategory(@RequestBody CategoryDto request) throws Exception {
        ApiBaseResponse response = new ApiBaseResponse();
        Category category = new Category();
        if(StringUtils.isEmpty(request.getCategoryName())) {
            response.setStatus(204);
            response.setSuccess(false);
            response.setMessage("Category name is empty!");
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
        category.setCategoryName(request.getCategoryName());
        try {
            Category result = categoriesService.updateCategory(category);
            response.setStatus(200);
            response.setSuccess(true);
            response.setMessage("Update category successfully!");
            Map<String, Object> map = new HashMap<>();
            map.put("categoryUpdated", result);
            response.setData(map);
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            response.setSuccess(false);
            response.setMessage("Update category failure!");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/category/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> deleteCategory(@PathVariable int id) {
        ApiBaseResponse response = new ApiBaseResponse();
        try{
            categoriesService.deleteCategory(id);
            response.setSuccess(true);
            response.setStatus(200);
            response.setMessage("Delete category successfully!");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
            response.setSuccess(false);
            response.setMessage("Delete category failure!");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
