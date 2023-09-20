package com.vtortsev.quizapp.controller;


import com.vtortsev.quizapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }
    //http://localhost:8080/admin?id=1&action=delete
    @PostMapping
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) Integer id,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            userService.deleteUser(id);
        }
        return "redirect:/admin";
    }

    @GetMapping("/gt/{id}")
    public String  gtUser(@PathVariable("userId") Integer id, Model model) {
        model.addAttribute("allUsers", userService.usergtList(id));
        return "admin";
    }
}