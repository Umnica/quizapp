package com.vtortsev.quizapp;

import com.vtortsev.quizapp.dao.AnswerDao;
import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.service.AnswerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AnswerServiceIntegrationTestFindAll {
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
}
