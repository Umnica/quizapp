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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
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
    @Test
    void testGetAnswerById() {
        // Создание поддельного ответа
        Answer answer = new Answer();
        answer.setId(1);
        answer.setAnswerText("Простой вопрос?");

        // Замокировать методы DAO
        when(answerDao.findById(1)).thenReturn(java.util.Optional.of(answer));

        Answer retrievedAnswer = answerService.getAnswerById(1);

        assertNotNull(retrievedAnswer);
        assertEquals(answer.getId(), retrievedAnswer.getId());
        assertEquals(answer.getAnswerText(), retrievedAnswer.getAnswerText());
    }

    @Test
    void testValidAnswerCreation() {
        CreateAnswerDto createAnswerDto = new CreateAnswerDto();
        createAnswerDto.setAnswerText("Valid answer text");

        // Замокировать методы DAO
        when(answerDao.save(any(Answer.class))).thenAnswer(invocation -> {
            Answer answer = invocation.getArgument(0);
            answer.setId(1); // Присваиваем идентификатор, как если бы было сохранение в базу
            return answer;
        });

        Answer validAnswer = answerService.createAnswer(createAnswerDto);

        assertNotNull(validAnswer);
        assertEquals("Valid answer text", validAnswer.getAnswerText());
        assertNotNull(validAnswer.getId()); // Удостоверимся, что у объекта есть идентификатор
    }


}
