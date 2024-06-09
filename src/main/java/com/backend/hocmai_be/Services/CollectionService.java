package com.backend.hocmai_be.Services;

import com.backend.hocmai_be.DTO.request.CollectionReq;
import com.backend.hocmai_be.DTO.response.CollectionRes;
import com.backend.hocmai_be.DTO.response.CourseRes;
import com.backend.hocmai_be.Model.Collection;
import com.backend.hocmai_be.Model.Course;
import com.backend.hocmai_be.Repositories.CollectionRepository;
import com.backend.hocmai_be.Repositories.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CollectionService {
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private CourseRepository courseRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public List<CollectionRes> getAllCollections() {
        List<Collection> collections = collectionRepository.findAll();
        return collections.stream().map(this::convertToRes).collect(Collectors.toList());
    }

    @Transactional
    public CollectionRes getCollectionByName(String collectionName) {
        Optional<Collection> collection = collectionRepository.findByCollectionName(collectionName);

        return collection.map(this::convertToRes).orElse(null);
    }

    public CollectionRes saveCollection(CollectionReq collectionReq) {
        Collection collection = new Collection();
        collection.setCollectionName(collectionReq.getCollectionName());

        Set<Course> courses = new HashSet<>();
        for (Integer courseId : collectionReq.getCourseIds()) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy khoá học có ID: " + courseId));
            courses.add(course);
        }
        collection.setCourses(courses);
        Collection savedCollection = collectionRepository.save(collection);
        return convertToRes(savedCollection);
    }

    private CollectionRes convertToRes(Collection collection) {
        CollectionRes collectionDTO = modelMapper.map(collection, CollectionRes.class);
        Set<CourseRes> courseRess = collection.getCourses().stream()
                .map(course -> modelMapper.map(course, CourseRes.class))
                .collect(Collectors.toSet());
        collectionDTO.setCourses(courseRess);
        return collectionDTO;
    }
}
