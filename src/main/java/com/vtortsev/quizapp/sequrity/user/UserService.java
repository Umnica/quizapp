package com.vtortsev.quizapp.sequrity.user;

import com.vtortsev.quizapp.sequrity.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserDao userDao;
    private final String USER_NOT_FOUND_MSG = "Пользователь email = \"%s\" не найден";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findByUsername(email)
                .orElseThrow(() ->
                new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void save(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(Role.USER);

        userDao.save(user);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
