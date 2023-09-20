package com.vtortsev.quizapp.serviseTests;

import com.vtortsev.quizapp.dao.QuestionDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateQuestionDtoWithUseIdsAnswerAndCategory;
import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.entities.Category;
import com.vtortsev.quizapp.entities.Question;
import com.vtortsev.quizapp.service.AnswerService;
import com.vtortsev.quizapp.service.CategoryService;
import com.vtortsev.quizapp.service.QuestionService;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
public class QuestionServiceTest {

    @Mock
    private QuestionDao questionDao;

    @Mock
    private AnswerService answerService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private QuestionService questionService;



    @Test
    void testGetAllQuestion() {
        List<Question> expectedQuestions = new ArrayList<>();

        when(questionDao.findAll()).thenReturn(expectedQuestions);

        List<Question> actualQuestions = questionService.getAllQuestions();

        assertEquals(expectedQuestions, actualQuestions);
    }

    @Test
    void testCreateQuestionDtoWithUseIdsAnswerAndCategory() {
        Answer answer1 = new Answer();
        answer1.setId(1);
        answer1.setAnswerText("Answer 1");
        when(answerService.getAnswerById(1)).thenReturn(answer1);

        Answer answer2 = new Answer();
        answer2.setId(2);
        answer2.setAnswerText("Answer 2");
        when(answerService.getAnswerById(2)).thenReturn(answer2);

        Category category1 = new Category();
        category1.setId(1);
        category1.setName("Category 1");
        when(categoryService.getCategoryById(1)).thenReturn(category1);

        Category category2 = new Category();
        category2.setId(2);
        category2.setName("Category 2");
        when(categoryService.getCategoryById(2)).thenReturn(category2);

        CreateQuestionDtoWithUseIdsAnswerAndCategory dto = getDto();
        when(questionDao.save(any(Question.class))).thenAnswer(invocation -> {
            Question savedQuestion = invocation.getArgument(0);
            savedQuestion.setId(1);
            return savedQuestion;
        });

        Question createdQuestion = questionService.createQuestionDtoWithUseIdsAnswerAndCategory(dto);

        assertNotNull(createdQuestion.getId());
        assertEquals(dto.getQuestionText(), createdQuestion.getQuestionText());
        assertEquals(dto.getLevel(), createdQuestion.getLevel());
        assertEquals(2, createdQuestion.getAnswers().size());
        assertEquals(2, createdQuestion.getCategories().size());
    }

    @NotNull
    private static CreateQuestionDtoWithUseIdsAnswerAndCategory getDto() {
        List<Integer> answersIds = new ArrayList<>();

        answersIds.add(1);
        answersIds.add(2);

        List<Integer> categoriesIds = new ArrayList<>();
        categoriesIds.add(1);
        categoriesIds.add(2);

        CreateQuestionDtoWithUseIdsAnswerAndCategory dto = new CreateQuestionDtoWithUseIdsAnswerAndCategory();
        dto.setQuestionText("Test question?");
        dto.setLevel("Test level");
        dto.setAnswers(answersIds);
        dto.setCategories(categoriesIds);
        return dto;
    }
}
