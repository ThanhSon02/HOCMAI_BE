package com.backend.hocmai_be.Controllers;

import com.backend.hocmai_be.DTO.request.CollectionReq;
import com.backend.hocmai_be.DTO.response.ApiBaseResponse;
import com.backend.hocmai_be.DTO.response.CollectionRes;
import com.backend.hocmai_be.Services.CollectionService;
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
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/public/api/collection/getAll")
    public ResponseEntity<List<CollectionRes>> getAllCollections() {
        List<CollectionRes> collections = collectionService.getAllCollections();
        return new ResponseEntity<>(collections, HttpStatus.OK);
    }
    @PostMapping("/api/collection/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ApiBaseResponse> createCollection(@RequestBody CollectionReq request) {
        ApiBaseResponse response = new ApiBaseResponse();
        CollectionRes savedCollection = collectionService.saveCollection(request);
        Map map = new HashMap<>();
        map.put("collectionSaved", savedCollection);
        response.setSuccess(true);
        response.setStatus(200);
        response.setData(map);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/public/api/collection")
    public ResponseEntity<ApiBaseResponse> getCollectionByName(@RequestBody CollectionReq request) {
        ApiBaseResponse response = new ApiBaseResponse();
        CollectionRes collection = collectionService.getCollectionByName(request.getCollectionName());
        if (collection != null) {
            Map map = new HashMap();
            map.put("collection", collection);
            response.setSuccess(true);
            response.setData(map);
            response.setStatus(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setSuccess(false);
            response.setMessage("Không tìm thấy danh sách");
            response.setStatus(500);
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/public/api/collection")
//    public ResponseEntity<ApiBaseResponse> getCollectionById(@RequestBody CollectionReq request) {
//        ApiBaseResponse response = new ApiBaseResponse();
//        CollectionRes collection = collectionService.getCollectionByName(request.getCollectionName());
//        if (collection != null) {
//            Map map = new HashMap();
//            map.put("collection", collection);
//            response.setSuccess(true);
//            response.setData(map);
//            response.setStatus(200);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } else {
//            response.setSuccess(false);
//            response.setMessage("Không tìm thấy danh sách");
//            response.setStatus(500);
//            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
//        }
//    }
}
