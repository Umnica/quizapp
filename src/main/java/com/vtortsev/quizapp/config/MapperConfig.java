package com.vtortsev.quizapp.config;


import com.vtortsev.quizapp.dto.mapper.AnswerMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public AnswerMapper answerMapper() {
        return Mappers.getMapper(AnswerMapper.class);
    }
}