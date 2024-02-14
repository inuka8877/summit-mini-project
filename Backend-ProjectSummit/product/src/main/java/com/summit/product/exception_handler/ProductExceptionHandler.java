package com.summit.product.exception_handler;

import com.summit.product.exception.CustomException;
import com.summit.product.response.ErrorResponseObject;
import com.summit.product.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponseObject>handleCustomException(CustomException ex){
        ErrorResponseObject errorResponse = new ErrorResponseObject(ex);
        return new ResponseEntity<>(errorResponse,ex.getStatuscode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseObject>handleArgumentNotValidException(MethodArgumentNotValidException ex){
        CustomException ce = new CustomException(ex.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
        ErrorResponseObject errorResponse = new ErrorResponseObject(ce);
        return new ResponseEntity<>(errorResponse, ce.getStatuscode());
    }
}
