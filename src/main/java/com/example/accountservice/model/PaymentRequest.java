package com.example.accountservice.model;

import lombok.Data;

@Data
public class PaymentRequest {
    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getDebitAccount() {
        return debitAccount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    private String debitAccount;
    private String creditAccount;
    private double amount;


}
