package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.QuestionDao;
import com.vtortsev.quizapp.dto.createEntityDto.CreateFullQuestionDto;
import com.vtortsev.quizapp.dto.createEntityDto.CreateQuestionDto;
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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service // сервис какая то логика внутри
public class QuestionService {
    private final QuestionDao questionDao; // это объект для работы с бд DataAccessObject
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public QuestionService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }


    public List<Question> getAllQuestions() {
        return questionDao.findAll();
        /*// не работает,
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
        Root<Question> root = criteriaQuery.from(Question.class);
        criteriaQuery.select(root);
        return entityManager.createQuery(criteriaQuery).getResultList();
        */
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDao.findByCategoriesName(category);
    }

    public List<Question> getQuestionByLevel(String level) {
        return questionDao.findByLevel(level);
    }

    /*public Question addQuestion(Question question) {
        return questionDao.save(question);
    }*/

    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }

    public Question createQuestion(CreateQuestionDto createQuestionDto) {
        Question question = new Question();
        question.setQuestionText(createQuestionDto.getQuestionText());
        question.setLevel(createQuestionDto.getLevel());
        return questionDao.save(question);
    }
    @Transactional
    public Question createQuestion(CreateFullQuestionDto createFullQuestionDto) {
        Question question = new Question();
        question.setQuestionText(createFullQuestionDto.getQuestionText());
        question.setLevel(createFullQuestionDto.getLevel());

        List<Answer> answers = createFullQuestionDto.getAnswers()
                .stream()
                .map(answerDto -> {
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
                        Category newCategory = new Category();
                        newCategory.setName(categoryDto.getName());
                        return newCategory;
                    }
                })
                .collect(Collectors.toSet());

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

    public Question getQuestionById(Integer id) {
        return questionDao.findById(id).orElse(null);
    }

    private Category findCategoryByName(String categoryName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);
        Root<Category> root = query.from(Category.class);
        query.where(criteriaBuilder.equal(root.get("name"), categoryName));
        TypedQuery<Category> typedQuery = entityManager.createQuery(query);
        typedQuery.setMaxResults(1);

        List<Category> result = typedQuery.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}


