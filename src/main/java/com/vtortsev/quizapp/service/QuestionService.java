package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.QuestionDao;
import com.vtortsev.quizapp.entities.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Slf4j
@Service // сервис какая то логика внутри
public class QuestionService {
    // это объект для работы с бд DataAccessObject
    private final QuestionDao questionDao;


    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }


    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategory(category);
    }
    public List<Question> getQuestionByLevel(String level) {
        return questionDao.findByLevel(level);
    }

    public Question addQuestion(Question question) {
        // тут можно использовать коды состояния для вывода ошибки
        // https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#information_responses
        return questionDao.save(question);
    }

    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }
}
