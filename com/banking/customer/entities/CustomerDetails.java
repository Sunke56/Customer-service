package com.banking.customer.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="customer_details")
@Entity
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean openBankAccount;
    private String mobile;
    @OneToOne
    @JoinColumn(name ="bank_account_id",referencedColumnName="id" )
    private BankAccountDetails bankAccountDetails;
}
