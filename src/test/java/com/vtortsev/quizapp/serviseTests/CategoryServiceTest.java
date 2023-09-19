package com.vtortsev.quizapp.serviseTests;

import com.vtortsev.quizapp.dao.CategoryDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateCategoryDto;
import com.vtortsev.quizapp.entities.Category;
import com.vtortsev.quizapp.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryServiceTest {
    @Mock
    private CategoryDao categoryDao;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void testGetCategoryById() {
        Integer categoryId = 1;

        Category category = new Category();
        category.setId(categoryId);
        category.setName("TestCategory");

        when(categoryDao.findById(categoryId)).thenReturn(Optional.of(category));

        Category actualCategory = categoryService.getCategoryById(categoryId);

        assertEquals(category, actualCategory);
    }

    @Test
    void testGetAllCategory() {
        List<Category> categories = new ArrayList<>();

        Category c1 = new Category();
        Category c2 = new Category();

        c1.setName("Category 1");
        c2.setName("Category 2");

        categories.add(c1);
        categories.add(c2);

        when(categoryDao.findAll()).thenReturn(categories);

        List<Category> actualCategories = categoryService.getAllCategory();

        assertEquals(categories, actualCategories);
    }
    @Test
    void testCreateCategory() {
        CreateCategoryDto dto = new CreateCategoryDto();
        dto.setName("Category 1");

        Category category = new Category();
        category.setName("Category 1");

        when(categoryDao.save(any(Category.class))).thenAnswer(invocation -> {
            Category savedCategory = invocation.getArgument(0);  // Получить аргумент, переданный методу сохранения
            savedCategory.setId(1);  // Устанавлеваем id образца для сохраненной категории
            return savedCategory;
        });
        Category actualCategory = categoryService.createCategory(dto);

        assertNotNull(actualCategory.getId());
        assertEquals(category.getName(), actualCategory.getName());
    }



}
