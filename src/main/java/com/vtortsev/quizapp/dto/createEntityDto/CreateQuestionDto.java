package com.vtortsev.quizapp.dto.createEntityDto;

import com.vtortsev.quizapp.entities.Answer;
import lombok.Data;
import org.intellij.lang.annotations.Pattern;

import java.util.HashSet;

@Data
public class CreateQuestionDto {
    private String level;
    private String questionText;

}
