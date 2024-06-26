package com.example.demo.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class CustomBaseException extends RuntimeException{
    private HttpStatus httpStatus;
    private SimpleResponse simpleResponse;
    public CustomBaseException(HttpStatus httpStatus, SimpleResponse simpleResponse) {
        this.httpStatus = httpStatus;
        this.simpleResponse = simpleResponse;
    }


}