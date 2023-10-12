package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.dto.FullQuestionDto;
import com.vtortsev.quizapp.dto.QuestionDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateQuestionDtoWithUseIdsAnswerAndCategory;
import com.vtortsev.quizapp.dto.mapper.QuestionMapper;
import com.vtortsev.quizapp.entities.Question;
import com.vtortsev.quizapp.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController // главный контролер, по запросам пользователя выводит файл ему
@RequestMapping("/questions") //у всех заросах в этом блоке контролера будет впереди /questions
@AllArgsConstructor
public class QuestionController {
    // spring сам создаст бин и поместит его в переменную
    private final QuestionService questionService;

    private final QuestionMapper questionMapper;



    @GetMapping
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return questions.stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public FullQuestionDto getQuestionById(@PathVariable Integer id) {
        return questionMapper.toFullDto(questionService.getQuestionById(id));
    }


    @GetMapping("/category/{category}")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    public FullQuestionDto createQuestionDtoWithUseIdsAnswerAndCategory(@RequestBody CreateQuestionDtoWithUseIdsAnswerAndCategory dto) {
        return questionMapper.toFullDto(questionService.createQuestionDtoWithUseIdsAnswerAndCategory(dto));
    }



    @GetMapping("/delete/{id}")
    public void deleteQuestion(@PathVariable Integer id) {
        questionService.deleteQuestion(id);
    }
}
