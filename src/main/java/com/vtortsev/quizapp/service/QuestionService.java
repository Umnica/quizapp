package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.entities.Question;
import com.vtortsev.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }
    public List<Question> getQuestionsByLevel(String level) {
        return questionDao.findByLevel(level);
    }

    public void addQuestion(Question question) {
        // тут можно использовать коды состояния для вывода ошибки
        // https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#information_responses
        questionDao.save(question);
    }
    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }
}
