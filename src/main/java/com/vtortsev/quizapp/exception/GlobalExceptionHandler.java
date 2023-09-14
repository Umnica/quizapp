package com.vtortsev.quizapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
@Slf4j
@ControllerAdvice
//содержит методы-обработчики для исключений
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//если возникает любое исключение
    @PostMapping("/add")
    public ResponseEntity<String> handleException(Exception ex) {
        //возвращает ответ со статусом 500 и сообщением "An error occurred"
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        //добавить `@RequestMapping` или `@PostMapping`
    }
}
