package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.QuestionDao;
import com.vtortsev.quizapp.entities.Question;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Question addQuestion(Question question) {
        return questionDao.save(question);
    }

    public void deleteQuestion(Integer id) {
        questionDao.deleteById(id);
    }

    public Question getQuestionById(Integer id) {
        return questionDao.findById(id).orElse(null);
    }
}
