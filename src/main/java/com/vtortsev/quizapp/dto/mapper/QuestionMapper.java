package com.vtortsev.quizapp.dto.mapper;

import com.vtortsev.quizapp.dto.QuestionDto;
import com.vtortsev.quizapp.entities.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface QuestionMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "level", target = "level")
    @Mapping(source = "questionText", target = "questionText")
    QuestionDto toDto(Question question);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "level", target = "level")
    @Mapping(source = "questionText", target = "questionText")
    Question toEntity(QuestionDto questionDto);
}
