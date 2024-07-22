package com.banking.customer.repositories;

import com.banking.customer.entities.BankAccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailsRepository extends JpaRepository<BankAccountDetails,Long> {
}
