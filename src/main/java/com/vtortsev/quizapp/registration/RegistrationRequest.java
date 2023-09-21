package com.vtortsev.quizapp.registration;

import lombok.Data;

@Data
public class RegistrationRequest {
    private final String name;
    private final String username;
    private final String email;
    private final String password;

}

