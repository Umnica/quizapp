package com.vtortsev.quizapp.sequrity.dao;

import com.vtortsev.quizapp.sequrity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findUserByName(String name);
}
