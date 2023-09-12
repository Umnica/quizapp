package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.Question;
import com.vtortsev.quizapp.service.QuestionService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
// главный контролер, по запросам пользователя выводит файл ему
@RestController
@RequestMapping("question") //у всех заросах в этом блоке контролера будет впереди /question
public class QuestionController {
    // spring сам создаст бин и поместит его в переменную
    @Autowired
    QuestionService questionService;


    // хочу вернуть объект ответа пользователю и статус ошибки
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")// берет из запроса переменнную category и передает в метод
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("level/{level}")
    public ResponseEntity<List<Question>> getQuestionsByLevel(@PathVariable String level) {
        return questionService.getQuestionsByLevel(level);
    }
       // добавление строки в бд, если все хорошо вывод success, исполтзуем пост
       // со стороны клиента хочу получать объект Question в json формате
       // Spring автоматически переводит JSON в тип Java, если указан соответствующий тип.

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
    @GetMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        return questionService.deleteQuestion(id);
    }



}
