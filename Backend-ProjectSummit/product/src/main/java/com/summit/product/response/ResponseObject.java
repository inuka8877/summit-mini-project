package com.summit.product.response;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
public class ResponseObject {
    private HttpStatus status;
    private Object data;
    private final String success="success";

    public ResponseObject(Object data, HttpStatus httpStatus){
        this.data=data;
        this.status=httpStatus;
    }
}
