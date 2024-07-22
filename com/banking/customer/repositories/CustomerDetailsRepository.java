package com.banking.customer.repositories;

import com.banking.customer.entities.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDetailsRepository extends JpaRepository<CustomerDetails, Long> {

    Optional<CustomerDetails>findByEmailAndMobile(String email, String mobile);

    CustomerDetails findByEmailAndPassword(String email, String password);

   Optional<CustomerDetails> findByIdAndBankAccountDetailsAccountNumber(Long customerId,String bankAccountNumber);
}