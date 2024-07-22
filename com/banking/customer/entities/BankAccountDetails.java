package com.banking.customer.entities;

import com.banking.customer.enums.BankAccountType;
import com.banking.customer.enums.Status;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="bank_account_details")
public class BankAccountDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private BankAccountType accountType;
    private String aadhar;
    private String pan;
    private String accountNumber;
    private double balance;
    @Enumerated(EnumType.STRING)
    private Status status;

}
