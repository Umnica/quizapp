package com.vtortsev.quizapp.sequrity.controller;

import com.vtortsev.quizapp.sequrity.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
    private UserService userService;

    @GetMapping
    public String registrationForm() {
        return "registration"; // Возвращает страницу с формой регистрации
    }
    /*
    @PostMapping
    public String registerUser(@ModelAttribute RegistrationForm registrationForm) {
        // Производите сохранение нового пользователя в базу данных
        userService.registerUser(registrationForm);
        return "redirect:/login"; // После успешной регистрации перенаправляйте на страницу входа
    }
    */
}
