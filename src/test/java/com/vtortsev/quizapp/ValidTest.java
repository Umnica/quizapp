package com.vtortsev.quizapp;

import com.vtortsev.quizapp.dao.CategoryDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateAnswerDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateCategoryDto;
import com.vtortsev.quizapp.service.AnswerService;
import com.vtortsev.quizapp.service.CategoryService;
import com.vtortsev.quizapp.service.Valid;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ValidTest {
    @Test
    void testValidText() {
        assertTrue(Valid.isValidText("Valid, answer-1 text?"));
    }
    @Test
    void testInvalidTextWithSpecialCharacters() {
        assertFalse(Valid.isValidText("Invalid answer text @#!"));
        assertFalse(Valid.isValidText("Invalid answer-- text!"));
        assertFalse(Valid.isValidText("Invalid answ,,,er text"));
        assertFalse(Valid.isValidText(""));
    }
    @InjectMocks
    private AnswerService answerService;
    @Test // тест на выброс исключения при неправильным входным параметром
    void testInvalidAnswerCreation() {
        CreateAnswerDto createAnswerDto = new CreateAnswerDto();
        createAnswerDto.setAnswerText("Invalid answer @#!"); // Invalid answer text

        assertThrows(IllegalArgumentException.class, () -> answerService.createAnswer(createAnswerDto));
    }



    @InjectMocks
    private CategoryService categoryService;

    @Test
    void testInvalidCategoryCreation() {
        // Устанавливаем имя категории меньше 2 символов
        CreateCategoryDto createCategoryDto = new CreateCategoryDto();
        createCategoryDto.setName("A");

        // Проверяем, что IllegalArgumentException вызывается при именах менее 2 символов
        assertThrows(IllegalArgumentException.class, () -> categoryService.createCategory(createCategoryDto));
    }


}
