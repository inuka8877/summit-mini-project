package com.summit.order.response;

import com.summit.order.exception.CustomException;
import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Data
@ToString
public class ErrorResponseObject {
    private HttpStatus status;
    private ErrorObject error=new ErrorObject();
    private final String success="error";

    public ErrorResponseObject(CustomException ex){
        this.status=ex.getStatuscode();
        this.error.setHttpstatus(ex.getStatuscode());
        this.error.setMessage(ex.getMessage());
    }
}
