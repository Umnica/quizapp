package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.service.AnswerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;
    @Autowired
    public AnswerController(AnswerService answerService) { this.answerService = answerService; }


    @GetMapping
    @ResponseBody
    public List<Answer> getAllAnswer() { return answerService.getAllAnswer(); }
}
