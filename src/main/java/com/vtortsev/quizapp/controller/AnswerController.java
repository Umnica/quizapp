package com.vtortsev.quizapp.controller;

import com.vtortsev.quizapp.dto.AnswerDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateAnswerDto;
import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.dto.mapper.AnswerMapper;
import com.vtortsev.quizapp.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper answerMapper;

    @Autowired
    public AnswerController(AnswerService answerService, AnswerMapper answerMapper) {
        this.answerService = answerService;
        this.answerMapper = answerMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnswerDto createAnswer(@RequestBody CreateAnswerDto createAnswerDto) {
        return answerMapper.toDto(answerService.createAnswer(createAnswerDto));
    }

    @GetMapping
    @ResponseBody
    public List<AnswerDto> getAllAnswer() {
        List<Answer> answers = answerService.getAllAnswer();
        return answers.stream()
                .map(answerMapper::toDto)
                .collect(Collectors.toList());
    }
}
