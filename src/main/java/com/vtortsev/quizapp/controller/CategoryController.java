package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.dto.CategoryDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateCategoryDto;
import com.vtortsev.quizapp.dto.mapper.CategoryMapper;
import com.vtortsev.quizapp.entities.Category;
import com.vtortsev.quizapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto createCategory(@RequestBody CreateCategoryDto createCategoryDto) {
        return categoryMapper.toDto(categoryService.createCategory(createCategoryDto));
    }

    @GetMapping
    public List<CategoryDto> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

}
