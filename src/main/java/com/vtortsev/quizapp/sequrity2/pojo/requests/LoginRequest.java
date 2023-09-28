package com.vtortsev.quizapp.sequrity2.pojo.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;

}
