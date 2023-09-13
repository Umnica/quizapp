package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.entities.Questions;
import com.vtortsev.quizapp.service.QuestionsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // главный контролер, по запросам пользователя выводит файл ему
@RequestMapping("/questions") //у всех заросах в этом блоке контролера будет впереди /questions
public class QuestionsController {
    // spring сам создаст бин и поместит его в переменную
    private final QuestionsService questionService;
    @Autowired
    public QuestionsController(QuestionsService questionService){
        this.questionService = questionService;
    }

    @GetMapping
    public List<Questions> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public List<Questions> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/level/{level}")
    public List<Questions> getQuestionsByLevel(@PathVariable String level) {
        return questionService.getQuestionsByLevel(level);
    }

    @PostMapping("/add")
    public Questions addQuestion(@RequestBody Questions question) {
        return questionService.addQuestion(question);
    }
    @GetMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
    }
}
