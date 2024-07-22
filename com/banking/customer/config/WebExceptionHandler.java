package com.banking.customer.config;

import com.banking.customer.dto.response.ErrorResponse;
import com.banking.customer.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class WebExceptionHandler {

@ExceptionHandler(ValidationException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
@ResponseBody
    public ErrorResponse handleValidationException(ValidationException exception){

        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setStatus("BAD_REQUEST");
        errorResponse.setMessage("Invalid Request");
        List<String> errors=new ArrayList<>();
        errors.add("add your error messages");
        errorResponse.setErrors(exception.getErrors());
        return errorResponse;

    }


}
