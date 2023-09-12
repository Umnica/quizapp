package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.Question;
import com.vtortsev.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

// сервис какая то логика внутри
@Service
public class QuestionService {
    // это объект для работы с бд DataAccessObject
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category) {
        return questionDao.findByCategory(category);
    }
    public List<Question> getQuestionsByLevel(String level) {
        return questionDao.findByLevel(level);
    }
    public ResponseEntity<String> addQuestion(Question question) {
        // тут можно использовать коды состояния для вывода ошибки
        // https://developer.mozilla.org/en-US/docs/Web/HTTP/Status#information_responses
        try{
            questionDao.save(question);
            return new ResponseEntity<>("success",HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }
    public String deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return "success";
    }
}
