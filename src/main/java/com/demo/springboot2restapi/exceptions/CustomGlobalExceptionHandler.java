package com.demo.springboot2restapi.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        CustomErrorDetail customErrorDetails = new CustomErrorDetail(
                new Date(),
                "From MethodArgumentNotValid Exception in GlobalExceptionHandler" +
                        "Method Argument Invalid",
                ex.getMessage());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        CustomErrorDetail customErrorDetails = new CustomErrorDetail(
                new Date(),
                "From MethodArgumentNotValid Exception in GlobalExceptionHandler" +
                        "Method Not Allowed",
                ex.getMessage());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(UserNameNotFoundException.class)
    public final ResponseEntity<Object> handleUserNameNotFoundException(
            UserNameNotFoundException ex, WebRequest request) {
        CustomErrorDetail customErrorDetails = new CustomErrorDetail(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleContraintViolationException(
            ConstraintViolationException ex, WebRequest webRequest
    ){
        CustomErrorDetail customErrorDetails = new CustomErrorDetail(
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }


}
