package com.vtortsev.quizapp.sequrity.service;

import com.vtortsev.quizapp.sequrity.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findUserByName(String name);
    String saveUser(User user);
    void deleteUser(Integer id);
}
