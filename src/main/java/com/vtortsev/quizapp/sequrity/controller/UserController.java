package com.vtortsev.quizapp.sequrity.controller;

import com.vtortsev.quizapp.sequrity.model.User;
import com.vtortsev.quizapp.sequrity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public List<User> findAllUser() {
        return userService.findAllUsers();
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<User> findUserByName(@PathVariable String name){
        return userService.findUserByName(name);
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @GetMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
