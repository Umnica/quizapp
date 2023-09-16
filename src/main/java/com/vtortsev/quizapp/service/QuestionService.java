package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.QuestionDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateFullQuestionDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateQuestionDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateQuestionDtoWithUseIdsAnswerAndCategory;
import com.vtortsev.quizapp.entities.Answer;
import com.vtortsev.quizapp.entities.Category;
import com.vtortsev.quizapp.entities.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service // сервис какая то логика внутри
public class QuestionService {
    private final QuestionDao questionDao; // это объект для работы с бд DataAccessObject
    private final CategoryService categoryService;
    private final AnswerService answerService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
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

    public Question createQuestion(CreateQuestionDto createQuestionDto) {
        Question question = new Question();
        question.setQuestionText(createQuestionDto.getQuestionText());
        question.setLevel(createQuestionDto.getLevel());
        return questionDao.save(question);
    }


    public Question getQuestionById(Integer id) {
        return questionDao.findById(id).orElse(null);
    }

    @Transactional
    public Question createQuestion(CreateFullQuestionDto createFullQuestionDto) {
        // Проверки на валидность текстов
        if (!Valid.isValidText(createFullQuestionDto.getLevel()))
            throw new IllegalArgumentException("Invalid question level");
        if (!Valid.isValidText(createFullQuestionDto.getQuestionText()))
            throw new IllegalArgumentException("Invalid question questionText");

        Question question = new Question();
        question.setQuestionText(createFullQuestionDto.getQuestionText());
        question.setLevel(createFullQuestionDto.getLevel());

        // Проверка и привязка существующих ответов по id
        List<Answer> answers = createFullQuestionDto.getAnswers()
                .stream()
                .map(answerDto -> {
                    if (!Valid.isValidText(answerDto.getAnswerText()))
                        throw new IllegalArgumentException("Invalid answer answerText");

                    Answer existingAnswer = entityManager.find(Answer.class, answerDto.getId());
                    if (existingAnswer == null) {
                        throw new IllegalArgumentException("Answer with id " + answerDto.getId() + " not found.");
                    }

                    return existingAnswer;
                })
                .collect(Collectors.toList());

        // Проверка и привязка существующих категорий по id
        Set<Category> categories = createFullQuestionDto.getCategories()
                .stream()
                .map(categoryDto -> {
                    if (!Valid.isValidCategoryName(categoryDto.getName()))
                        throw new IllegalArgumentException("Invalid category name");

                    Category existingCategory = entityManager.find(Category.class, categoryDto.getId());
                    if (existingCategory == null) {
                        throw new IllegalArgumentException("Category with id " + categoryDto.getId() + " not found.");
                    }

                    return existingCategory;
                })
                .collect(Collectors.toSet());

        // Проверка минимального количества ответов для категории История
        if (categories.stream().anyMatch(category -> category.getName().equals("История")) &&
                answers.size() < 2) {
            throw new IllegalArgumentException("Вопросы по категории История должны обязательно содержать минимум 2 ответа");
        }

        // Присваиваем ответы и категории вопросу
        question.setAnswers(answers);
        question.setCategories(categories);

        // Сохраняем вопрос в базу данных
        entityManager.persist(question);

        return question;
    }

    public Question createQuestionDtoWithUseIdsAnswerAndCategory(CreateQuestionDtoWithUseIdsAnswerAndCategory dto) {
        if (!Valid.isValidText(dto.getLevel()))
            throw new IllegalArgumentException("Invalid question level");
        if (!Valid.isValidText(dto.getQuestionText()))
            throw new IllegalArgumentException("Invalid question questionText");

        Question question = new Question();
        question.setQuestionText(dto.getQuestionText());
        question.setLevel(dto.getLevel());

        // Получаем ответы по их id
        List<Answer> answers = new ArrayList<>();
        for (Integer id: dto.getAnswers()) {
            Answer answer = answerService.getAnswerById(id);
            if (answer == null)
                throw new IllegalArgumentException("В базе данных не существует ответа с id: " + id);
            answers.add(answer);
        }
        List<Category> categories = new ArrayList<>();
        for (Integer id: dto.getCategories()) {
            Category category = categoryService.getCategoryById(id);
            if (category == null)
                throw new IllegalArgumentException("В базе данных не существует категории с id: " + id);
            categories.add(category);
        }

        question.setAnswers(answers);
        question.setCategories(new HashSet<>(categories));

        // Проверка на минимальное количество ответов для категории "История"
        if (categories.stream().anyMatch (category -> category.getName().equals("История"))
                && answers.size() < 2) {
            throw new IllegalArgumentException("Вопросы по категории История должны обязательно содержать минимум 2 ответа");
        }

        return questionDao.save(question);
    }

    /*private Category findCategoryByName(String categoryName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.where(criteriaBuilder.equal(root.get("name"), categoryName));
        TypedQuery<Category> typedQuery = entityManager.createQuery(query);
        typedQuery.setMaxResults(1);

        List<Category> result = typedQuery.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }*/

    /*
    @Transactional
    public Question createQuestion(CreateFullQuestionDto createFullQuestionDto) {
        if (!Valid.isValidText(createFullQuestionDto.getLevel()))
            throw new IllegalArgumentException("Invalid question level");
        if (!Valid.isValidText(createFullQuestionDto.getQuestionText()))
            throw new IllegalArgumentException("Invalid question questionText");


        Question question = new Question();
        question.setQuestionText(createFullQuestionDto.getQuestionText());
        question.setLevel(createFullQuestionDto.getLevel());

        List<Answer> answers = createFullQuestionDto.getAnswers()
                .stream()
                .map(answerDto -> {
                    if (!Valid.isValidText(answerDto.getAnswerText()))
                        throw new IllegalArgumentException("Invalid answer answerText");

                    Answer answer = new Answer();
                    answer.setAnswerText(answerDto.getAnswerText());
                    return answer;
                })
                .collect(Collectors.toList());

        Set<Category> categories = createFullQuestionDto.getCategories()
                .stream()
                .map(categoryDto -> {
                    // Проверка существования категории по имени
                    Category existingCategory = findCategoryByName(categoryDto.getName());
                    if (existingCategory != null) {
                        // Если категория существует, используем ее id
                        return existingCategory;
                    } else {
                        // Если категория не существует, создаем новую
                        if (!Valid.isValidCategoryName(categoryDto.getName()))
                            throw new IllegalArgumentException("Invalid category name");

                        Category newCategory = new Category();
                        newCategory.setName(categoryDto.getName());
                        return newCategory;
                    }
                })
                .collect(Collectors.toSet());

        // Проверка минимального количества ответов для категории "История"
        if (categories.stream().anyMatch(category -> category.getName().equals("История")) &&
                answers.size() < 2) {
            throw new IllegalArgumentException("Вопросы по категории История должны содержать минимум 2 ответа");
        }

        // Сохраняем ответы и категории в базу данных
        answers.forEach(entityManager::persist);
        categories.forEach(entityManager::persist);

        // Присваиваем сохраненные ответы и категории вопросу
        question.setAnswers(answers);
        question.setCategories(categories);

        // Сохраняем вопрос в базу данных
        entityManager.persist(question);

        return question;
    }


     */

     /*// не работает,
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
        Root<Question> root = criteriaQuery.from(Question.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
        */
}


