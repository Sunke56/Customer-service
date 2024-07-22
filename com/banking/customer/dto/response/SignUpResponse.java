package com.banking.customer.dto.response;

import lombok.Data;

@Data
public class SignUpResponse {
    private String message;
    private BankDetailsResponse bankAccountDetails;
}
