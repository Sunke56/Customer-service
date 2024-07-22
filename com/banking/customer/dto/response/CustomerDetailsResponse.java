package com.banking.customer.dto.response;

import lombok.Data;

@Data
public class CustomerDetailsResponse {
    private long id;
    private String email;
    private boolean activeBankAccount;
    private BankDetailsResponse bankDetailsResponse;
}
