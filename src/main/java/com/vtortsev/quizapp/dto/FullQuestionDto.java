package com.vtortsev.quizapp.dto;

import lombok.Data;
import java.util.List;

@Data
public class FullQuestionDto {
    private Integer id;
    private String level;
    private String questionText;

    private List<AnswerDto> answers;
    private List<CategoryDto> categories;
}
