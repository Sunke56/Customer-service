package com.banking.customer.services;

import com.banking.customer.dto.request.LoginRequest;
import com.banking.customer.dto.request.SignUpRequest;
import com.banking.customer.dto.response.CustomerDetailsResponse;
import com.banking.customer.dto.response.LoginResponse;
import com.banking.customer.dto.response.SignUpResponse;
import com.banking.customer.entities.CustomerDetails;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
   SignUpResponse signup(SignUpRequest signUpRequest) throws Exception;

   LoginResponse login(LoginRequest loginRequest) throws Exception;

   ResponseEntity<Object> getCustomerDetails(long customerId);

}
