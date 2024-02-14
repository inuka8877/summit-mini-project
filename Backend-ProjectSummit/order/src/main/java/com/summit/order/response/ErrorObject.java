package com.summit.order.response;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
public class ErrorObject {
    private HttpStatus httpstatus;
    private String message;
}
