package com.vtortsev.quizapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;

@ControllerAdvice
//содержит методы-обработчики для исключений
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//если возникает любое исключение
    @PostMapping("/add")
    public ResponseEntity<String> handleException(Exception ex) {
        //мы возвращаем ответ со статусом 500 и сообщением "An error occurred"
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        //добавить `@RequestMapping` или `@PostMapping`
    }
}
