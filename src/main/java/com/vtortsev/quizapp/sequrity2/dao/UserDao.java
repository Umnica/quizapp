package com.vtortsev.quizapp.sequrity2.dao;

import com.vtortsev.quizapp.sequrity2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
