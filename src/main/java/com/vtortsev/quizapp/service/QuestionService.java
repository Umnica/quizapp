package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.entities.Question;
import com.vtortsev.quizapp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// сервис какая то логика внутри
@Service
public class QuestionService {
    // это объект для работы с бд DataAccessObject
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>> getQuestionsByLevel(String level) {
        try{
            return new ResponseEntity<>(questionDao.findByLevel(level),HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<String> deleteQuestion(Integer id) {
        try{
            questionDao.deleteById(id);
            return new ResponseEntity<>("success",HttpStatus.OK);
        } catch (Exception ex){
            ex.printStackTrace();
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
    }
}
