package com.vtortsev.quizapp.dao;

import com.vtortsev.quizapp.entities.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersDao extends JpaRepository<Answers, Integer> {

}
