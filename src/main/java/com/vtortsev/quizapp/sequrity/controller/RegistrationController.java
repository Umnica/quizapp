package com.vtortsev.quizapp.sequrity.controller;


import com.vtortsev.quizapp.sequrity.user.User;
import com.vtortsev.quizapp.sequrity.user.UserDto;
import com.vtortsev.quizapp.sequrity.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private final UserService userService;

    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }
    @GetMapping("/l")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping
    public String registerUser(@RequestBody UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";
    }
}
