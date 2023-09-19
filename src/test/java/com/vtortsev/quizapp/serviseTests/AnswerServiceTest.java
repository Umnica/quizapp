package com.vtortsev.quizapp.serviseTests;

import com.vtortsev.quizapp.dao.AnswerDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateAnswerDto;
import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.service.AnswerService;
import com.vtortsev.quizapp.service.Valid;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class AnswerServiceTest {
    @Mock
    private AnswerDao answerDao;

    @InjectMocks
    private AnswerService answerService;

    @Test
    void testGetAllAnswer() {
        // Создаем список ответов для имитации ответа от базы данных
        List<Answer> expectedAnswers = new ArrayList<>();
        Answer answer = new Answer();
        Answer answer2 = new Answer();

        answer.setAnswerText("Answer 1");
        answer2.setAnswerText("Answer 2");

        expectedAnswers.add(answer);
        expectedAnswers.add(answer2);

        // Имитируем работу DAO
        when(answerDao.findAll()).thenReturn(expectedAnswers);

        // Вызываем метод для получения ответов
        List<Answer> actualAnswers = answerService.getAllAnswer();

        // Проверяем, что список ответов соответствует ожидаемому
        assertEquals(expectedAnswers, actualAnswers);
    }
    @Autowired
    private AnswerService answerService1;
    @Test
    void testGetAnswerById() {

        CreateAnswerDto answer = new CreateAnswerDto();

        answer.setAnswerText("Простой вопрос?");

        Answer savedAnswer = answerService1.createAnswer(answer);
        Answer retrievedAnswer = answerService1.getAnswerById(savedAnswer.getId());

        assertNotNull(retrievedAnswer);
        assertEquals(savedAnswer.getId(), retrievedAnswer.getId());
        assertEquals("Простой вопрос?", retrievedAnswer.getAnswerText());
    }

    @Test
    void testValidAnswerCreation() {
        CreateAnswerDto createAnswerDto = new CreateAnswerDto();
        createAnswerDto.setAnswerText("Valid answer text");

        Answer validAnswer = answerService1.createAnswer(createAnswerDto);
        assertNotNull(validAnswer);
    }


}
