package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.QuestionsDao;
import com.vtortsev.quizapp.entities.Questions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Service // сервис какая то логика внутри
public class QuestionsService {
    // это объект для работы с бд DataAccessObject
    private QuestionsDao questionsDao;

    @Autowired
    public QuestionsService(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }


    public List<Questions> getAllQuestions() {
        return questionsDao.findAll();
    }

    public List<Questions> getQuestionsByCategory(String category) {
        return questionsDao.findByCategory(category);
    }
    public List<Questions> getQuestionsByLevel(String level) {
        return questionsDao.findByLevel(level);
    }

    public Questions addQuestion(Questions question) {
        // тут можно использовать коды состояния для вывода ошибки
        // https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#information_responses
        return questionsDao.save(question);
    }

    public void deleteQuestion(Integer id) {
        questionsDao.deleteById(id);
    }
}
