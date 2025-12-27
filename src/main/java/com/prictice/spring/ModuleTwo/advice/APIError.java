package com.prictice.spring.ModuleTwo.advice;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIError {

    private LocalDateTime localDateTime;
    private HttpStatus status;
    private String message;
    private List<String> subErrors;

}
