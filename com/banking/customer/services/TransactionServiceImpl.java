package com.banking.customer.services;

import com.banking.customer.entities.BankAccountDetails;
import com.banking.customer.entities.CustomerDetails;
import com.banking.customer.exceptions.ValidationException;
import com.banking.customer.repositories.BankDetailsRepository;
import com.banking.customer.repositories.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{
@Autowired
    private CustomerDetailsRepository customerDetailsRepository;
@Autowired
   private BankDetailsRepository bankDetailsRepository;
    @Override
    public ResponseEntity<Object> updateBankBalance(Long customerId,String bankAccountNumber,boolean isDebit,Long amount) {

   Optional<CustomerDetails> byIdAndBankAccountDetailsaccountNumber = customerDetailsRepository.findByIdAndBankAccountDetailsAccountNumber(customerId,bankAccountNumber);
   if(byIdAndBankAccountDetailsaccountNumber.isPresent()){
       CustomerDetails customerDetails=byIdAndBankAccountDetailsaccountNumber.get();
       BankAccountDetails bankAccountDetails=customerDetails.getBankAccountDetails();
       double finalBalance= isDebit ? bankAccountDetails.getBalance()-amount : bankAccountDetails.getBalance()+amount;
       bankAccountDetails.setBalance(finalBalance);
       bankDetailsRepository.save(bankAccountDetails);
       return new ResponseEntity<>("Bank account update successfully", HttpStatus.OK);

   }else{
      throw new ValidationException("Invalid bank account");
   }
    }
}
