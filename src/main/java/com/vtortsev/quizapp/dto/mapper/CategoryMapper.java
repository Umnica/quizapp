package com.vtortsev.quizapp.dto.mapper;

import com.vtortsev.quizapp.dto.CategoryDto;
import com.vtortsev.quizapp.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CategoryMapper {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    CategoryDto toDto(Category category);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    Category toEntity(CategoryDto categoryDto);

}
