package com.vtortsev.quizapp.dao;

import com.vtortsev.quizapp.entities.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // интерфейс репозитория расширяет интерфейс JpaRepository<Question, Integer>
public interface QuestionsDao extends JpaRepository<Questions, Integer> {
    List<Questions> findByCategory(String category);
    List<Questions> findByLevel(String level);
}
