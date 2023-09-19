package com.vtortsev.quizapp.serviseTests;

import com.vtortsev.quizapp.dao.QuestionDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateAnswerDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateCategoryDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateQuestionDtoWithUseIdsAnswerAndCategory;
import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.entities.Category;
import com.vtortsev.quizapp.entities.Question;
import com.vtortsev.quizapp.service.AnswerService;
import com.vtortsev.quizapp.service.CategoryService;
import com.vtortsev.quizapp.service.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class QuestionServiceTest {
    @Mock
    private QuestionDao questionDao;

    @InjectMocks
    private QuestionService questionService;

    @Test
    void testGetQuestionById() {
        Question question = new Question();
        Integer questionId = 1;

        question.setQuestionText("Вопросы?");
        question.setLevel("80 level");

        when(questionDao.findById(questionId)).thenReturn(Optional.of(question));

        Question actualQuestion = questionService.getQuestionById(questionId);

        Assertions.assertEquals(question, actualQuestion);
    }

    @Autowired
    private AnswerService answerService;
    @Autowired
    private CategoryService categoryService;

    @Test
    void testGetAllQuestion() {
        List<Question> questions = new ArrayList<>();

        Question q1 = new Question();
        Question q2 = new Question();

        q1.setQuestionText("Вопросы?");
        q1.setLevel("80 level");

        Set<Category> cList = new HashSet<>();
        Category c = new Category();
        c.setName("Category");
        cList.add(c);
        q1.setCategories(cList);

        List<Answer> as = new ArrayList<>();
        Answer a = new Answer();
        a.setAnswerText("Answer 1");
        as.add(a);
        q1.setAnswers(as);

        when(questionDao.findAll()).thenReturn(questions);

        List<Question> actualQuestions = questionService.getAllQuestions();

        assertEquals(questions, actualQuestions);
    }
    @Test
    void testCreateQuestionDtoWithUseIdsAnswerAndCategory() {
        // Создание данных
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
