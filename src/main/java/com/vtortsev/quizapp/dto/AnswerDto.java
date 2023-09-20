package com.vtortsev.quizapp.dto;

import lombok.Data;

@Data
public class AnswerDto {
    private Integer id;
    private String answerText;
    private QuestionDto question;
}
