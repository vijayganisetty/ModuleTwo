package com.prictice.spring.ModuleTwo.advice;

import com.prictice.spring.ModuleTwo.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> exceptionHandler(ResourceNotFoundException e){
        APIError apiError = APIError.builder()
                .status(HttpStatus.NOT_FOUND)
                .localDateTime(LocalDateTime.now())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> internalServerExceptionHandler(Exception e){
        APIError apiError =APIError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .localDateTime(LocalDateTime.now())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIError> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        List<String> errors = e.getAllErrors().stream().map(
                error -> error.getDefaultMessage()
        ).collect(Collectors.toList());
        APIError apiError =  APIError.builder()
                .localDateTime(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .subErrors(errors)
                .message("invalid input from user").build();
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
