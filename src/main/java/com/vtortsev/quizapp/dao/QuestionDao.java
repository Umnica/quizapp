package com.vtortsev.quizapp.dao;

import com.vtortsev.quizapp.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // интерфейс репозитория расширяет интерфейс JpaRepository<Question, Integer>
public interface QuestionDao extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    List<Question> findByLevel(String level);
}
