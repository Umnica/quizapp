package com.vtortsev.quizapp.dto.createEntityDto;

import lombok.Data;
import org.intellij.lang.annotations.Pattern;

@Data
public class CreateQuestionDto {
    private String level;
    private String questionText;
}
