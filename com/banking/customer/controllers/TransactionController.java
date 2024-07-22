package com.banking.customer.controllers;

import com.banking.customer.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customer/")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @PostMapping("{customerId}/bank/{bankAccountNumber}")
    public ResponseEntity<Object> updateBankBalance(@RequestParam("isDebit") boolean isDebit,
                                                    @RequestParam("amount") Long amount,
                                                    @PathVariable("customerId") Long customerId,
                                                    @PathVariable("bankAccountNumber") String bankAccountNumber){
        return transactionService.updateBankBalance(customerId,bankAccountNumber,isDebit,amount);
    }

}
