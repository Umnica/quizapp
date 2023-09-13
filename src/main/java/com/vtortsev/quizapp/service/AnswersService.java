package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.AnswersDao;
import com.vtortsev.quizapp.entities.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnswersService {
    private AnswersDao answersDao;
    @Autowired
    public AnswersService(AnswersDao answersDao) { this.answersDao = answersDao; }


    public List<Answers> getAllAnswers() { return answersDao.findAll(); }
}
