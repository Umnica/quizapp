package com.vtortsev.quizapp;

import com.vtortsev.quizapp.dao.AnswerDao;
import com.vtortsev.quizapp.dao.CategoryDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateCategoryDto;
import com.vtortsev.quizapp.entities.Category;
import com.vtortsev.quizapp.service.AnswerService;
import com.vtortsev.quizapp.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
@SpringBootTest
public class CategoryServiceTests {
    @Mock
    private CategoryDao categoryDao;
    @InjectMocks
    private CategoryService categoryService;
    @Test
    @Transactional
    void testGetCategoryById() {
        Integer categoryId = 1;
        Category expectedCategory = new Category();
        expectedCategory.setId(categoryId);
        expectedCategory.setName("TestCategory");

        when(categoryDao.findById(categoryId)).thenReturn(Optional.of(expectedCategory));

        Category actualCategory = categoryService.getCategoryById(categoryId);

        assertEquals(expectedCategory, actualCategory);
        // Устанавливаем имя категории меньше 2 символов
        CreateCategoryDto createCategoryDto = new CreateCategoryDto();
        createCategoryDto.setName("A");

        // Проверяем, что IllegalArgumentException вызывается при именах менее 2 символов
        assertThrows(IllegalArgumentException.class, () -> categoryService.createCategory(createCategoryDto));
    }

    @Test
    @Transactional
    void testGetAllCategory() {
        List<Category> expectedCategories = new ArrayList<>();
        Category category = new Category();
        category.setName("Category 1");
        expectedCategories.add(category);
        Category category2 = new Category();
        category2.setName("Category 2");
        expectedCategories.add(category2);


        when(categoryDao.findAll()).thenReturn(expectedCategories);

        List<Category> actualCategories = categoryService.getAllCategory();

        assertEquals(expectedCategories, actualCategories);
    }

    @Test
    @Transactional
    void testCreateCategory() {
        CreateCategoryDto createCategoryDto = new CreateCategoryDto();
        createCategoryDto.setName("TestCategory");

        Category expectedCategory = new Category();
        expectedCategory.setName("TestCategory");

        when(categoryDao.save(expectedCategory)).thenReturn(expectedCategory);

        Category actualCategory = categoryService.createCategory(createCategoryDto);

        assertEquals(expectedCategory, actualCategory);
    }
}
