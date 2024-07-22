package com.banking.customer.services;

import org.springframework.http.ResponseEntity;

public interface TransactionService {

    ResponseEntity<Object> updateBankBalance( Long customerId, String bankAccountNumber,boolean isDebit, Long amount);
}
