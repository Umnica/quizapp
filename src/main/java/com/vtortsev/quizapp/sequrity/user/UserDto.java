package com.vtortsev.quizapp.sequrity.user;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class UserDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
