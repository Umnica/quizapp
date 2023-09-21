package com.vtortsev.quizapp.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "works"; // тип посмотреть что остельное все ок
    }
}
