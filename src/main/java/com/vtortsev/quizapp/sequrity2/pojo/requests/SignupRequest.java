package com.vtortsev.quizapp.sequrity2.pojo.requests;

import lombok.Data;

import java.util.Set;

@Data
public class SignupRequest {
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
}
