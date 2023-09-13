package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.entities.Question;
import com.vtortsev.quizapp.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController // главный контролер, по запросам пользователя выводит файл ему
@RequestMapping("/question") //у всех заросах в этом блоке контролера будет впереди /question
public class QuestionController {
    // spring сам создаст бин и поместит его в переменную
    private final QuestionService questionService;
    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    // хочу вернуть объект ответа пользователю и статус ошибки
    @GetMapping("/all")
    public List<Question> getAllQuestions() {
        log.info("Вызван метод getAllQuestions()");
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")// берет из запроса переменнную category и передает в метод
    public List<Question> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/level/{level}")
    public List<Question> getQuestionsByLevel(@PathVariable String level) {
        return questionService.getQuestionsByLevel(level);
    }
       // добавление строки в бд, если все хорошо вывод success, исполтзуем пост
       // со стороны клиента хочу получать объект Question в json формате
       // Spring автоматически переводит JSON в тип Java, если указан соответствующий тип.

    @PostMapping("/add")
    public Question addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
    @GetMapping("/delete")
    public void deleteQuestion(@RequestBody Question question) {
        questionService.deleteQuestion(question);
    }

}
