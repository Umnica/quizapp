package com.vtortsev.quizapp.dao;

import com.vtortsev.quizapp.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // интерфейс репозитория расширяет интерфейс JpaRepository<Question, Integer>
public interface QuestionDao extends JpaRepository<Question, Integer> {

    @Query("SELECT q FROM Question q JOIN q.categories c WHERE c.name = :categoryName")
    List<Question> findQuestionsByCategoryName(@Param("categoryName") String categoryName);

    List<Question> findByLevel(String level);
}
