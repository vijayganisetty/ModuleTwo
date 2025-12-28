package com.prictice.spring.ModuleTwo.advice;

import com.prictice.spring.ModuleTwo.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> exceptionHandler(ResourceNotFoundException e){
        APIError apiError = APIError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(e.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> internalServerExceptionHandler(Exception e){
        APIError apiError =APIError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
        return buildErrorResponseEntity(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        List<String> errors = e.getAllErrors().stream().map(
                error -> error.getDefaultMessage()
        ).collect(Collectors.toList());
        APIError apiError =  APIError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .subErrors(errors)
                .message("invalid input from user").build();
        return buildErrorResponseEntity(apiError);
    }

    private ResponseEntity<ApiResponse<?>> buildErrorResponseEntity(APIError apiError) {
        return new ResponseEntity<>(new ApiResponse<>(apiError), apiError.getStatus());
    }
}
