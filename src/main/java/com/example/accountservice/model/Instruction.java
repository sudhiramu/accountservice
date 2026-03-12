package com.example.accountservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Instruction {
    public Instruction(String accountNumber, String currency, String instructionType, double amount, String branch, String bank) {
        this.accountNumber = accountNumber;
        this.currency = currency;
        this.instructionType = instructionType;
        this.amount = amount;
        this.branch = branch;
        this.bank = bank;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(String instructionType) {
        this.instructionType = instructionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getBank() { return bank; }
    public void setBank(String bank) { this.bank = bank; }

    private String accountNumber;
    private String currency;
    private String instructionType; // DEBIT or CREDIT
    private double amount;
    private String branch;
    private String bank;
}
