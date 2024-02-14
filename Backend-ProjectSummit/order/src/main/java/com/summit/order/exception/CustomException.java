package com.summit.order.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    private HttpStatus statuscode;

    public CustomException(String message,HttpStatus statuscode){
        super(message);
        this.statuscode=statuscode;
    }

    public HttpStatus getStatuscode() {
        return statuscode;
    }
}