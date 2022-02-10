package com.demo.springboot2restapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {
    @ExceptionHandler(UserNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetail usernameNotFound(UserNameNotFoundException ex){
        return new CustomErrorDetail(new Date(),
                "from @Restcontrolleradvice",
                ex.getMessage());
    }
}
