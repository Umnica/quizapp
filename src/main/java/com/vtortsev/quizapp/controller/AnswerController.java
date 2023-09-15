package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.dto.AnswerDto;
import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.dto.mapper.AnswerMapper;
import com.vtortsev.quizapp.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @Autowired
    public AnswerController(AnswerService answerService, AnswerMapper answerMapper) {
        this.answerService = answerService;
        this.answerMapper = answerMapper;
    }


    //@GetMapping
    //@ResponseBody
    //public List<Answer> getAllAnswer(){ return answerService.getAllAnswer(); }

    @GetMapping
    @ResponseBody
    public List<AnswerDto> getAllAnswer() {
        List<Answer> answers = answerService.getAllAnswer();
        return answers.stream()
                .map(answerMapper::toDto)
                .collect(Collectors.toList());
    }
}
