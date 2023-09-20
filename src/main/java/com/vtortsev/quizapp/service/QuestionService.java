package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.AnswerDao;
import com.vtortsev.quizapp.dao.CategoryDao;
import com.vtortsev.quizapp.dao.QuestionDao;
import com.vtortsev.quizapp.dto.CategoryDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateQuestionDtoWithUseIdsAnswerAndCategory;
import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.entities.Category;
import com.vtortsev.quizapp.entities.Question;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // сервис (какая то логика внутри)
public class QuestionService {
    private final QuestionDao questionDao; // это объект для работы с бд DataAccessObject
    private final CategoryService categoryService;
    private final AnswerService answerService;


    //@Autowired
    public QuestionService(QuestionDao questionDao, CategoryService categoryService, AnswerService answerService) {
        this.questionDao = questionDao;
        this.categoryService = categoryService;
        this.answerService = answerService;
    }


    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategoriesName(category);
    }

    public List<Question> getQuestionByLevel(String level) {
        return questionDao.findByLevel(level);
    }

    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }

    public Question getQuestionById(Integer id) {
        return questionDao.findById(id).orElse(null);
    }

    @Transactional
    public Question createQuestionDtoWithUseIdsAnswerAndCategory(CreateQuestionDtoWithUseIdsAnswerAndCategory dto) {

        if (!Valid.isValidText(dto.getLevel()))
            throw new IllegalArgumentException("Invalid question level");
        if (!Valid.isValidText(dto.getQuestionText()))
            throw new IllegalArgumentException("Invalid question questionText");

        Question question = new Question();
        if (dto.getId() != null)
            question.setId(dto.getId());
        question.setQuestionText(dto.getQuestionText());
        question.setLevel(dto.getLevel());

        if (dto.getAnswers().isEmpty())
            throw new IllegalArgumentException("Id ответов не могут быть пустыми");
        if (dto.getCategories().isEmpty())
            throw new IllegalArgumentException("Id категорий не могут быть пустыми");
        // Получаем ответы по их id
        List<Answer> answers = new ArrayList<>();
        for (Integer id : dto.getAnswers()) {
            Answer answer = answerService.getAnswerById(id);
            if (answer == null)
                throw new IllegalArgumentException("В базе данных не существует ответа с id: " + id);
            answers.add(answer);
        }

        List<Category> categories = new ArrayList<>();
        for (Integer id : dto.getCategories()) {
            Category category = categoryService.getCategoryById(id);
            if (category == null)
                throw new IllegalArgumentException("В базе данных не существует категории с id: " + id);
            categories.add(category);
        }

        // Проверка на минимальное количество ответов для категории "История"
        if (categories.stream().anyMatch(category -> category.getName().equals("История"))
                && answers.size() < 2) {
            throw new IllegalArgumentException("Вопросы по категории История должны обязательно содержать минимум 2 ответа");
        }

        List<Answer> answers1 = answers.stream().map(answer -> {
            Answer a = new Answer();
            a.setAnswerText(answer.getAnswerText());
            a.setQuestion(question);

            return answerService.createAnswer(a);
        }).toList();

        question.setAnswers(answers1);

        question.setCategories(categories);

        return questionDao.save(question);
    }

}


