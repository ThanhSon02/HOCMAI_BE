package com.backend.hocmai_be.Controllers;
import com.backend.hocmai_be.DTO.response.CategoryRes;
import com.backend.hocmai_be.Model.Category;
import com.backend.hocmai_be.DTO.response.ApiBaseResponse;
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
    public ResponseEntity<ApiBaseResponse> addCategory(@RequestBody CategoryRes categoryDto) {
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
                response.setStatus(400);
                return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
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

    @PutMapping("/api/category/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> updateCategory(@RequestBody CategoryRes request) {
        ApiBaseResponse response = new ApiBaseResponse();
        if(StringUtils.isEmpty(request.getCategoryName())) {
            response.setStatus(204);
            response.setSuccess(false);
            response.setMessage("Category name is empty!");
            return new ResponseEntity<>(response,HttpStatus.NO_CONTENT);
        }
        try {
            Category result = categoriesService.updateCategory(request);
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

    @DeleteMapping("/api/category/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> deleteCategory(@PathVariable int id) {
        ApiBaseResponse response = new ApiBaseResponse();
        try{
            categoriesService.deleteCategory(id);
            Map map = new HashMap();
            map.put("categoryDeleted", id);
            response.setSuccess(true);
            response.setStatus(200);
            response.setMessage("Delete category successfully!");
            response.setData(map);
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
