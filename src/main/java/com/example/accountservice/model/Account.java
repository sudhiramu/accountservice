package com.example.accountservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "accounts")
@Data                // Generates Getters, Setters, toString, etc.
@NoArgsConstructor   // Required by JPA
@AllArgsConstructor  // Useful for your data loader
public class Account {

    @Id
    private String accountNumber;
    private String accountHolderName;
    private String currency;
    private String branch;

    public Account() {} // Required by JPA

    public Account(String accountNumber, String accountHolderName, String currency, String branch) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.currency = currency;
        this.branch = branch;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getAccountHolderName() { return accountHolderName; }
    public String getCurrency() { return currency; }
    public String getBranch() { return branch; }

}