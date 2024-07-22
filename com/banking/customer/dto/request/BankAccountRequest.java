package com.banking.customer.dto.request;

import com.banking.customer.enums.BankAccountType;
import lombok.Data;

@Data
public class BankAccountRequest {
    private BankAccountType accountType;
    private String aadhar;
    private String pan;
}
