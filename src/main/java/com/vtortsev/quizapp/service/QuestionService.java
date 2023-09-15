package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.QuestionDao;
import com.vtortsev.quizapp.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // сервис какая то логика внутри
public class QuestionService {
    private final QuestionDao questionDao; // это объект для работы с бд DataAccessObject

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }


    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategoriesName(category);
    }

    public List<Question> getQuestionByLevel(String level) {
        return questionDao.findByLevel(level);
    }

    public Question addQuestion(Question question) {
        return questionDao.save(question);
    }

    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }

    public Question getQuestionById(Integer id) {
        return questionDao.findById(id).orElse(null);
    }
}
