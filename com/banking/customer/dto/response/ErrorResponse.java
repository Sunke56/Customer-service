package com.banking.customer.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class ErrorResponse {
    private String status;
    private String message;
    private List<String> errors;
}
