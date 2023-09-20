package com.vtortsev.quizapp.service;

import com.vtortsev.quizapp.dao.RoleDao;
import com.vtortsev.quizapp.dao.UserDao;
import com.vtortsev.quizapp.entities.Role;
import com.vtortsev.quizapp.entities.User;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }

        return user;
    }
    public User findUserById(Integer id) {
        Optional<User> userFromDb = userDao.findById(id);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userDao.findAll();
    }

    public Boolean saveUser(User user) {
        User userFromDB = userDao.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    public boolean deleteUser(Integer id) {
        if (userDao.findById(id).isPresent()) {
            userDao.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Integer idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

}
