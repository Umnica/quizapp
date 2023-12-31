package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.AnswerDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateAnswerDto;
import com.vtortsev.quizapp.dto.mapper.AnswerMapper;
import com.vtortsev.quizapp.entities.Answer;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AnswerService {
    private final AnswerDao answerDao;


    public Answer getAnswerById(Integer id) {
        return answerDao.findById(id).orElse(null);
    }


    public List<Answer> getAllAnswer() {
        return answerDao.findAll();
    }

    public Answer createAnswer(CreateAnswerDto createAnswerDto) {
        if (!Valid.isValidText(createAnswerDto.getAnswerText()))
            throw new IllegalArgumentException("Invalid answer text");

        Answer answer = new Answer();
        answer.setAnswerText(createAnswerDto.getAnswerText());
        return answerDao.save(answer);
    }

    public Answer createAnswer(Answer answerIn) {
        if (!Valid.isValidText(answerIn.getAnswerText()))
            throw new IllegalArgumentException("Invalid answer text");

        Answer answerOut = new Answer();
        answerOut.setAnswerText(answerIn.getAnswerText());
        answerOut.setQuestion(answerIn.getQuestion());
        return answerDao.save(answerOut);
    }
}
