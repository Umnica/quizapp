package com.vtortsev.quizapp;

import com.vtortsev.quizapp.dto.AnswerDto;
import com.vtortsev.quizapp.dto.createEntityDto.*;
import com.vtortsev.quizapp.dto.mapper.QuestionMapper;
import com.vtortsev.quizapp.entities.Category;
import com.vtortsev.quizapp.entities.Question;
import com.vtortsev.quizapp.service.AnswerService;
import com.vtortsev.quizapp.service.CategoryService;
import com.vtortsev.quizapp.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.vtortsev.quizapp.entities.Answer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionServiceTests {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private QuestionMapper questionMapper;

    @Test
    @Transactional
    void createQuestionDtoWithUseIdsAnswerAndCategory() {
        // Create test data
        List<Answer> answers = new ArrayList<>();
        CreateAnswerDto answer1 = new CreateAnswerDto();
        answer1.setAnswerText("Answer 1");
        answers.add(answerService.createAnswer(answer1));

        CreateAnswerDto answer2 = new CreateAnswerDto();
        answer2.setAnswerText("Answer 2");
        answers.add(answerService.createAnswer(answer2));

        List<Category> categories = new ArrayList<>();
        CreateCategoryDto category = new CreateCategoryDto();
        category.setName("Test Category");
        categories.add(categoryService.createCategory(category));

        List<Integer> answerIds = new ArrayList<>();
        answers.forEach(a -> answerIds.add(a.getId()));

        List<Integer> categoryIds = new ArrayList<>();
        categories.forEach(c -> categoryIds.add(c.getId()));

        CreateQuestionDtoWithUseIdsAnswerAndCategory dto = new CreateQuestionDtoWithUseIdsAnswerAndCategory();
        dto.setQuestionText("Test question?");
        dto.setLevel("Test level");
        dto.setAnswers(answerIds);
        dto.setCategories(categoryIds);

        Question createdQuestion = questionService.createQuestionDtoWithUseIdsAnswerAndCategory(dto);


        assertNotNull(createdQuestion.getId());
        assertEquals(dto.getQuestionText(), createdQuestion.getQuestionText());
        assertEquals(dto.getLevel(), createdQuestion.getLevel());
        assertEquals(answers.size(), createdQuestion.getAnswers().size());
        assertEquals(categories.size(), createdQuestion.getCategories().size());


        // Проверка валидности questionText
        dto.setLevel("Test level");
        dto.setQuestionText("Invalid question text with special characters @#$");
        assertThrows(IllegalArgumentException.class, () -> {
            questionService.createQuestionDtoWithUseIdsAnswerAndCategory(dto);
        });


    }

}