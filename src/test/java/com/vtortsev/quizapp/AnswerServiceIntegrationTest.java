package com.vtortsev.quizapp;

import com.vtortsev.quizapp.dto.createEntityDto.CreateAnswerDto;
import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.service.AnswerService;
import com.vtortsev.quizapp.service.Valid;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class AnswerServiceIntegrationTest {

    @Autowired
    private AnswerService answerService;

    @Test
    void testGetAnswerById() {
        CreateAnswerDto answer = new CreateAnswerDto();

        answer.setAnswerText("Простой вопрос?");

        Answer savedAnswer = answerService.createAnswer(answer);
        Answer retrievedAnswer = answerService.getAnswerById(savedAnswer.getId());

        assertNotNull(retrievedAnswer);
        assertEquals(savedAnswer.getId(), retrievedAnswer.getId());
        assertEquals("Простой вопрос?", retrievedAnswer.getAnswerText());
    }

    @Test
    void testValidAnswerText() {
        assertTrue(Valid.isValidText("Valid answer text"));
    }

    @Test
    void testInvalidAnswerTextWithSpecialCharacters() {
        assertFalse(Valid.isValidText("Invalid answer text @#!"));
        assertFalse(Valid.isValidText("Invalid answer-- text!"));
    }

    @Test
    void testInvalidAnswerTextEmpty() {
        assertFalse(Valid.isValidText(""));
    }

    @Test
    void testValidAnswerCreation() {
        CreateAnswerDto createAnswerDto = new CreateAnswerDto();
        createAnswerDto.setAnswerText("Valid answer text");

        Answer validAnswer = answerService.createAnswer(createAnswerDto);
        assertNotNull(validAnswer);
    }

    @Test
    void testInvalidAnswerCreation() {
        CreateAnswerDto createAnswerDto = new CreateAnswerDto();
        createAnswerDto.setAnswerText("Invalid answer @#!"); // Invalid answer text

        assertThrows(IllegalArgumentException.class, () -> answerService.createAnswer(createAnswerDto));
    }
}