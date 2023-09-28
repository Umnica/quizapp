package com.vtortsev.quizapp.sequrity2.service;

import com.vtortsev.quizapp.sequrity2.dao.UserDao;
import com.vtortsev.quizapp.sequrity2.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    UserDao userDao;
    private final static String USER_NOT_FOUND_MSG = "Пользователь не найден с username: %s";
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao
                .findUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username)));
        return UserDetailsImpl.build(user);
    }
}
