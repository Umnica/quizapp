package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.entities.Category;
import com.vtortsev.quizapp.service.AnswerService;
import com.vtortsev.quizapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) { this.categoryService = categoryService; }

    @GetMapping
    @ResponseBody
    public List<Category> getAllCategory() { return categoryService.getAllCategory(); }

}
