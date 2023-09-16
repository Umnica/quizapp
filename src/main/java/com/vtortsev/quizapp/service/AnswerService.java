package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.AnswerDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateAnswerDto;
import com.vtortsev.quizapp.entities.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    private final AnswerDao answerDao;

    @Autowired
    public AnswerService(AnswerDao answerDao) {
        this.answerDao = answerDao;
    }


    public List<Answer> getAllAnswer() {
        return answerDao.findAll();
    }

    public Answer createAnswer(CreateAnswerDto createAnswerDto) {
        Answer answer = new Answer();
        answer.setAnswerText(createAnswerDto.getAnswerText());
        return answerDao.save(answer);
    }
}
