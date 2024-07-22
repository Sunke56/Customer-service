package com.banking.customer.controllers;

import com.banking.customer.dto.request.LoginRequest;
import com.banking.customer.dto.request.SignUpRequest;
import com.banking.customer.dto.response.LoginResponse;
import com.banking.customer.dto.response.SignUpResponse;
import com.banking.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/customer/")
public class CustomerController {
    @Autowired
    private CustomerService customerService;



    @PostMapping("signup")
    public SignUpResponse signup(@RequestBody SignUpRequest signUpRequest) throws Exception{
       return customerService.signup(signUpRequest);
    }

    @PostMapping("login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws Exception{
        return customerService.login(loginRequest);
    }

    @GetMapping("{customerId}")
    public ResponseEntity<Object> getCustomerDetails(@PathVariable("customerId") long customerId){
        return customerService.getCustomerDetails(customerId);
    }





}
