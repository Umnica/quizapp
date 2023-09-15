package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.entities.Question;
import com.vtortsev.quizapp.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // главный контролер, по запросам пользователя выводит файл ему
@RequestMapping("/questions") //у всех заросах в этом блоке контролера будет впереди /questions
public class QuestionController {
    // spring сам создаст бин и поместит его в переменную
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }

    @GetMapping("/level/{level}")
    public List<Question> getQuestionByLevel(@PathVariable String level) {
        return questionService.getQuestionByLevel(level);
    }

    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
    }
}
