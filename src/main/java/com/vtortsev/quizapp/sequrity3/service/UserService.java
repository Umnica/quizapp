package com.vtortsev.quizapp.sequrity3.service;

import com.vtortsev.quizapp.sequrity3.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findUserByName(String name);
    String saveUser(User user);
}
