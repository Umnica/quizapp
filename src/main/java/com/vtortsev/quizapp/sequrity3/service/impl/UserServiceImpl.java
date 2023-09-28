package com.vtortsev.quizapp.sequrity3.service.impl;

import com.vtortsev.quizapp.sequrity3.model.User;
import com.vtortsev.quizapp.sequrity3.dao.UserDao;
import com.vtortsev.quizapp.sequrity3.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private PasswordEncoder passwordEncoder;
    private UserDao userDao;

    @Override
    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public String saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User user1 = userDao.save(user);
        if (user1 != null && !Objects.equals(user1.getName(), "")) {
            return "Пользователь успешно сохранен";
        }
        return "Попробуйте еще раз, пользователь не сохранен";
    }
}
