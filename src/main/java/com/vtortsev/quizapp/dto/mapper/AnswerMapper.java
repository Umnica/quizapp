package com.vtortsev.quizapp.dto.mapper;

import com.vtortsev.quizapp.dto.AnswerDto;
import com.vtortsev.quizapp.entities.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AnswerMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "answerText", target = "answerText")
    AnswerDto toDto(Answer answer);
}