package com.vtortsev.quizapp.validTests;

import com.vtortsev.quizapp.dto.createEntityDto.CreateAnswerDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateCategoryDto;
import com.vtortsev.quizapp.service.AnswerService;
import com.vtortsev.quizapp.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EntitiesValidTest {
    @InjectMocks
    private AnswerService answerService;
    @InjectMocks
    private CategoryService categoryService;
    @Test // тест на выброс исключения при неправильным входным параметром
    void testInvalidAnswerCreation() {
        CreateAnswerDto createAnswerDto = new CreateAnswerDto();
        createAnswerDto.setAnswerText("Invalid answer @#!"); // Invalid answer text

        assertThrows(IllegalArgumentException.class, () -> answerService.createAnswer(createAnswerDto));
    }

    @Test
    void testInvalidCategoryCreation() {
        // Устанавливаем имя категории меньше 2 символов
        CreateCategoryDto createCategoryDto = new CreateCategoryDto();
        createCategoryDto.setName("A");

        // Проверяем, что IllegalArgumentException вызывается при именах менее 2 символов
        assertThrows(IllegalArgumentException.class, () -> categoryService.createCategory(createCategoryDto));
    }
}
