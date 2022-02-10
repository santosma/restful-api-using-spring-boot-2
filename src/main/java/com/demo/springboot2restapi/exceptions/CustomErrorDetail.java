package com.demo.springboot2restapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CustomErrorDetail extends Exception {
    private Date timeStamp;
    private String message;
    private String errorDetails;
}
