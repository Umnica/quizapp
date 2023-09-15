package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.dto.CategoryDto;
import com.vtortsev.quizapp.dto.mapper.CategoryMapper;
import com.vtortsev.quizapp.entities.Category;
import com.vtortsev.quizapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

}
