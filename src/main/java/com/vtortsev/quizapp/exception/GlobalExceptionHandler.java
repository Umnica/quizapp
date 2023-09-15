package com.vtortsev.quizapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
//содержит методы-обработчики для исключений
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(HttpServletRequest request, Exception ex) {
        // Логируем ошибку
        logger.error("Error occurred for endpoint " + request.getRequestURI() + ": " + ex.getMessage());

        // Возвращаем статус 400 и сообщение об ошибке
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: " + ex.getMessage());
    }

    // Обработчик для исключений типа Error
    @ExceptionHandler(Error.class)
    public ResponseEntity<String> handleError(HttpServletRequest request, Error error) {
        // Логируем ошибку
        logger.error("Error occurred for endpoint " + request.getRequestURI() + ": " + error.getMessage());

        // Возвращаем статус 400 и сообщение об ошибке
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An error occurred: " + error.getMessage());
    }
}
