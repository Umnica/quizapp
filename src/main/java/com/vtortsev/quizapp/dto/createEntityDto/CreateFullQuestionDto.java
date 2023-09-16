package com.vtortsev.quizapp.dto.createEntityDto;

import com.vtortsev.quizapp.dto.AnswerDto;
import com.vtortsev.quizapp.dto.CategoryDto;
import lombok.Data;

import java.util.List;
@Data
public class CreateFullQuestionDto {
    private Integer id;
    private String level;
    private String questionText;

    private List<AnswerDto> answers;
    private List<CategoryDto> categories;
}
