package com.vtortsev.quizapp.sequrity2.pojo.responses;

import com.vtortsev.quizapp.sequrity2.entities.Role;
import lombok.Data;

import java.util.List;
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Integer id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Integer id, String username, String email, List<String> roles) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
