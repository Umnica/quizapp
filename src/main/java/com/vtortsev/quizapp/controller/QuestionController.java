package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.Question;
import com.vtortsev.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


// главный контролер, по запросам пользователя выводит файл ему
@RestController
@RequestMapping("question") //у всех заросах в этом блоке контролера будет впереди /question
public class QuestionController {
    // spring сам создаст бин и поместит его в переменную
    @Autowired
    QuestionService questionService;

    @GetMapping("allQuestions")
    public List<Question> getAllQuestions(){
        return  questionService.getAllQuestions();
    }


    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category){ // берет из запроса переменнную и передает в метод
        return questionService.getQuestionsByCategory(category);
    }
    @GetMapping("level/{level}")
    public List<Question> getQuestionsByLevel(@PathVariable String level){
        return questionService.getQuestionsByLevel(level);
    }
}
