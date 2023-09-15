package com.vtortsev.quizapp.config;


import com.vtortsev.quizapp.dto.mapper.AnswerMapper;
import com.vtortsev.quizapp.dto.mapper.CategoryMapper;
import com.vtortsev.quizapp.dto.mapper.QuestionMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public AnswerMapper answerMapper() {
        return Mappers.getMapper(AnswerMapper.class);
    }

    @Bean
    public QuestionMapper questionMapper() {
        return Mappers.getMapper(QuestionMapper.class);
    }

    @Bean
    public CategoryMapper categoryMapper() {
        return Mappers.getMapper(CategoryMapper.class);
    }


}