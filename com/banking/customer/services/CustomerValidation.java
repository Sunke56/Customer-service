package com.banking.customer.services;

import com.banking.customer.dto.request.SignUpRequest;
import com.banking.customer.exceptions.ValidationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class CustomerValidation {

    public void validatesignuprequest(SignUpRequest signUpRequest) throws Exception{
        if(StringUtils.isEmpty(signUpRequest.getFirstName())) {
            throw new ValidationException("First Name is mandatory");
        }
        if(signUpRequest.getLastName() == null) {
            throw new ValidationException("Last Name is mandatory");
        }
        if(signUpRequest.getEmail() == null) {
            throw new ValidationException("Email is mandatory");
        }
        if(signUpRequest.getMobile() == null) {
            throw new ValidationException("Mobile No is mandatory");
        }
        if(signUpRequest.getPassword() == null) {
            throw new ValidationException("Password is mandatory");
        }
        if(signUpRequest.getConformPassword() == null) {
            throw new ValidationException("Confirm Password is mandatory");
        }
        if(!signUpRequest.getPassword().equals(signUpRequest.getConformPassword())) {
            throw new ValidationException("Password and conform password should be same");
        }
        if (signUpRequest.isOpenBankAccount() && Objects.isNull(signUpRequest.getBankAccountRequest())){
            throw new ValidationException("Bank Account details mandatory");
        }

    }
}
