package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.dto.FullQuestionDto;
import com.vtortsev.quizapp.dto.QuestionDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateFullQuestionDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateQuestionDto;
import com.vtortsev.quizapp.dto.mapper.QuestionMapper;
import com.vtortsev.quizapp.entities.Question;
import com.vtortsev.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // главный контролер, по запросам пользователя выводит файл ему
@RequestMapping("/questions") //у всех заросах в этом блоке контролера будет впереди /questions
public class QuestionController {
    // spring сам создаст бин и поместит его в переменную
    private final QuestionService questionService;

    private final QuestionMapper questionMapper;


    @Autowired
    public QuestionController(QuestionService questionService, QuestionMapper questionMapper) {
        this.questionService = questionService;
        this.questionMapper = questionMapper;
    }


    @GetMapping
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return questions.stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toList());
        /*return questions.stream() // не работает
                .map(question -> {
                    // Принудительная инициализация ленивой загрузки
                    Hibernate.initialize(question.getAnswers());
                    Hibernate.initialize(question.getCategories());
                    return questionMapper.toDto(question);
                })
                .collect(Collectors.toList());*/
    }


    @GetMapping("/{id}")
    public FullQuestionDto getQuestionById(@PathVariable Integer id) {
        return questionMapper.toFullDto(questionService.getQuestionById(id));
    }


    @GetMapping("/category/{category}")
    public List<QuestionDto> getQuestionByCategory(@PathVariable String category) {
        List<Question> questions = questionService.getQuestionByCategory(category);
        return questions.stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/level/{level}")
    public List<QuestionDto> getQuestionByLevel(@PathVariable String level) {
        List<Question> questions = questionService.getQuestionByLevel(level);
        return questions.stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toList());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /*public QuestionDto createQuestion(@RequestBody CreateQuestionDto createQuestionDto) {
        return questionMapper.toDto(questionService.createQuestion(createQuestionDto));
    }*/
    public FullQuestionDto createFullQuestion(@RequestBody CreateFullQuestionDto createFullQuestionDto) {
        return questionMapper.toFullDto(questionService.createQuestion(createFullQuestionDto));
    }


    @GetMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
    }
}
