package com.backend.hocmai_be.Services;
import com.backend.hocmai_be.Model.Category;
import com.backend.hocmai_be.Repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriesService {
    private CategoriesRepository categoriesRepository;

    @Autowired
    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Category> findAll() {
        List<Category> categories = categoriesRepository.findAll();
        return categories;
    }

    public Category save(String categoryName) {
        Category category = new Category();
        category.setCategory_name(categoryName);
        Category categoryAdded = categoriesRepository.save(category);
        return categoryAdded;
    }

    public List<Category> findByCategoryName (String categoryName) {
        List<Category> categories = categoriesRepository.findCategoriesByName(categoryName);
        return categories;
    }
}
