package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.CategoryDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateCategoryDto;
import com.vtortsev.quizapp.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryService(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    public Category getCategoryById(Integer id) {
        return categoryDao.findById(id).orElse(null);
    }


    public List<Category> getAllCategory() {
        return categoryDao.findAll();
    }

    public Category createCategory(CreateCategoryDto createCategoryDto) {
        if (!Valid.isValidCategoryName(createCategoryDto.getName()))
            throw new IllegalArgumentException("Invalid category name");

        Category category = new Category();
        category.setName(createCategoryDto.getName());
        return categoryDao.save(category);
    }

    public List<Category> getCategoriesByIds(List<Integer> categoryIds) {
        return categoryIds.stream().map(this::getCategoryById).collect(Collectors.toList());
    }
}
