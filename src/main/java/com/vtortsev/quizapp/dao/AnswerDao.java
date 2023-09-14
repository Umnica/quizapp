package com.vtortsev.quizapp.dao;

import com.vtortsev.quizapp.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDao extends JpaRepository<Answer, Integer> {

}
