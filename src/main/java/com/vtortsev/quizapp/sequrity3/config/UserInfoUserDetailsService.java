package com.vtortsev.quizapp.sequrity3.config;

import com.vtortsev.quizapp.sequrity3.model.User;
import com.vtortsev.quizapp.sequrity3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class UserInfoUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.findUserByName(username);
        return user.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("HEY username not found"));
    }
}
