package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.entities.Answers;
import com.vtortsev.quizapp.service.AnswersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/answers")
public class AnswersController {
    private final AnswersService answersService;
    @Autowired
    public AnswersController(AnswersService answersService) { this.answersService = answersService; }


    @GetMapping
    public List<Answers> getAllAnswers(){ return answersService.getAllAnswers(); }
}
