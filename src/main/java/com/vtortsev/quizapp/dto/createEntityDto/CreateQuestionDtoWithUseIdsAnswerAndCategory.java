package com.vtortsev.quizapp.dto.createEntityDto;

import com.vtortsev.quizapp.dto.entityIdDto.AnswerIdDto;
import com.vtortsev.quizapp.dto.entityIdDto.CategoryIdDto;
import lombok.Data;

import java.util.List;
@Data
public class CreateQuestionDtoWithUseIdsAnswerAndCategory {
    private Integer id;
    private String level;
    private String questionText;

    private List<AnswerIdDto> answers;
    private List<CategoryIdDto> categories;
}
