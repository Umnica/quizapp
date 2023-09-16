package com.vtortsev.quizapp.dto.createEntityDto;

import lombok.Data;

import java.util.List;
@Data
public class CreateQuestionDtoWithUseIdsAnswerAndCategory {
    private Integer id;
    private String level;
    private String questionText;

    private List<Integer> answers;
    private List<Integer> categories;
}
