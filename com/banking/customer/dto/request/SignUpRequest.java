package com.banking.customer.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String conformPassword;
    private boolean openBankAccount;
    private String mobile;

    @JsonProperty("bankAccountDetails")
    private BankAccountRequest bankAccountRequest;
}
