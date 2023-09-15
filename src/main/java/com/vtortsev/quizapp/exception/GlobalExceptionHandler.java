package com.vtortsev.quizapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
//содержит методы-обработчики для исключений
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Логируем ошибку
        logger.error("An error occurred: " + ex.getMessage());

        // Возвращаем статус 400 и сообщение об ошибке
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: " + ex.getMessage());
    }

    // Обработчик для исключений типа Error
    @ExceptionHandler(Error.class)
    public ResponseEntity<String> handleError(Error error) {
        // Логируем ошибку
        logger.error("An error occurred: " + error.getMessage());

        // Возвращаем статус 400 и сообщение об ошибке
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: " + error.getMessage());
    }
}
