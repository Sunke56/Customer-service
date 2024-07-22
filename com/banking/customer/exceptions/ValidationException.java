package com.banking.customer.exceptions;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ValidationException extends RuntimeException {
    private List<String> errors = new ArrayList<>();

    public ValidationException(String message) {

        this.getErrors().add(message);
    }

    public ValidationException(List<String> errors) {

        this.getErrors().addAll(errors);
    }
}


