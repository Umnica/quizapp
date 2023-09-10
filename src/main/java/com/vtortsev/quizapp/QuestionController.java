package com.vtortsev.quizapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
public class QuestionController {
    @GetMapping("allQuestions")
    public String getAllQuestions(){
        int x = 6;
        int t = 5;
        return  "HI, Здесь твои вопросы";
    }
}
