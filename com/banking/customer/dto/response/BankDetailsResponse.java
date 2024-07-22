package com.banking.customer.dto.response;

import com.banking.customer.enums.BankAccountType;
import lombok.Data;

@Data
public class BankDetailsResponse {
   private BankAccountType accountType;
   private String accountNumber;
   private double balance;
}
