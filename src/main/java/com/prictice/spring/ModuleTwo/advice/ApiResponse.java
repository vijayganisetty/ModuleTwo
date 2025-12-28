package com.prictice.spring.ModuleTwo.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {


    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyy")
    private LocalDateTime localDateTime;
    private T data;
    private  APIError error;


    public ApiResponse() {
        this.localDateTime = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(APIError error) {
        this();
        this.error = error;
    }
}
