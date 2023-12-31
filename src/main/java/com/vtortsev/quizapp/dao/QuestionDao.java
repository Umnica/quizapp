package com.vtortsev.quizapp.dao;

import com.vtortsev.quizapp.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // интерфейс репозитория расширяет интерфейс JpaRepository<Question, Integer>
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategoriesName(String categoryName);

    List<Question> findByLevel(String level);
}
