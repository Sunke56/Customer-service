package com.banking.customer.services;

import com.banking.customer.dto.request.BankAccountRequest;
import com.banking.customer.dto.request.LoginRequest;
import com.banking.customer.dto.request.SignUpRequest;
import com.banking.customer.dto.response.BankDetailsResponse;
import com.banking.customer.dto.response.CustomerDetailsResponse;
import com.banking.customer.dto.response.LoginResponse;
import com.banking.customer.dto.response.SignUpResponse;
import com.banking.customer.entities.BankAccountDetails;
import com.banking.customer.entities.CustomerDetails;
import com.banking.customer.enums.Status;
import com.banking.customer.exceptions.ValidationException;
import com.banking.customer.repositories.BankDetailsRepository;
import com.banking.customer.repositories.CustomerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImp implements CustomerService{

    @Autowired
    private CustomerDetailsRepository customerDetailRepository;

    @Autowired
    private CustomerValidation customerValidation;
    @Autowired
    private BankDetailsRepository bankDetailsRepository;
    @Override
    public SignUpResponse signup(SignUpRequest signUpRequest) throws Exception {

        customerValidation.validatesignuprequest(signUpRequest);

        Optional<CustomerDetails> existingCustomer  = customerDetailRepository.findByEmailAndMobile(signUpRequest.getEmail(),signUpRequest.getMobile());
        if(existingCustomer.isPresent()){
            throw new ValidationException("Customer already Exist");
        }


        CustomerDetails customer=new CustomerDetails();

        customer.setFirstName(signUpRequest.getFirstName());
        customer.setLastName(signUpRequest.getLastName());
        customer.setEmail(signUpRequest.getEmail());
        customer.setPassword(signUpRequest.getPassword());
        customer.setMobile(signUpRequest.getMobile());
        customer.setOpenBankAccount(signUpRequest.isOpenBankAccount());

        if(signUpRequest.isOpenBankAccount()) {
            BankAccountDetails bankAccountDetails = mapToBankAccountDetails(signUpRequest.getBankAccountRequest());
            bankAccountDetails.setBalance(0);
            bankAccountDetails.setStatus(Status.ACTIVE);
            String accountNumber = UUID.randomUUID().toString();
            bankAccountDetails.setAccountNumber(accountNumber);
            bankAccountDetails = bankDetailsRepository.save(bankAccountDetails);
            customer.setBankAccountDetails(bankAccountDetails);
        }

        customerDetailRepository.save(customer);

        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setMessage("sign up successful");
        if(signUpRequest.isOpenBankAccount()){
            BankDetailsResponse bankDetailsResponse = new BankDetailsResponse();
            bankDetailsResponse.setAccountType(signUpRequest.getBankAccountRequest().getAccountType());
            bankDetailsResponse.setAccountNumber(customer.getBankAccountDetails().getAccountNumber());
            bankDetailsResponse.setBalance(customer.getBankAccountDetails().getBalance());
            signUpResponse.setBankAccountDetails(bankDetailsResponse);
        }

        return signUpResponse;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws Exception {

        LoginResponse loginResponse=new LoginResponse();
        CustomerDetails customerData= customerDetailRepository.findByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword());

        if(customerData==null){
            loginResponse.setMessage("Customer login failed");
        }
        else{
            loginResponse.setMessage("customer login successfully");
        }

        return loginResponse;

    }

    @Override
    public ResponseEntity<Object> getCustomerDetails(long customerId) {
      Optional<CustomerDetails> byId =customerDetailRepository.findById(customerId);
      if(byId.isPresent()){
          CustomerDetailsResponse customerDetailsResponse=new CustomerDetailsResponse();
          CustomerDetails customerDetails=byId.get();
          customerDetailsResponse.setId(customerDetails.getId());
          customerDetailsResponse.setEmail(customerDetails.getEmail());
          customerDetailsResponse.setActiveBankAccount(customerDetails.isOpenBankAccount());
          BankAccountDetails bankAccountDetails =customerDetails.getBankAccountDetails();
          BankDetailsResponse bankDetailsResponse =new BankDetailsResponse();
          bankDetailsResponse.setBalance(bankAccountDetails.getBalance());
          bankDetailsResponse.setAccountType(bankAccountDetails.getAccountType());
          bankDetailsResponse.setAccountNumber(bankAccountDetails.getAccountNumber());
          customerDetailsResponse.setBankDetailsResponse(bankDetailsResponse);
          return new ResponseEntity<>(customerDetailsResponse, HttpStatus.OK);

      }
      else{
           throw new ValidationException("Customer not found with Id: " +customerId);
      }
    }

    public BankAccountDetails mapToBankAccountDetails(BankAccountRequest bankAccountRequest){
        BankAccountDetails bankAccountDetails=new BankAccountDetails();
        bankAccountDetails.setAccountType(bankAccountRequest.getAccountType());
        bankAccountDetails.setAadhar(bankAccountRequest.getAadhar());
        bankAccountDetails.setPan(bankAccountRequest.getPan());
        return bankAccountDetails;
    }


}
