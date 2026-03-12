package com.example.accountservice.service;

import com.example.accountservice.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private AccountService accountService;

    public List<Instruction> createPaymentInstructions(PaymentRequest request) {
        // Step 1: Verify Accounts
        Account debit = accountService.getAccountDetails(request.getDebitAccount());
        Account credit = accountService.getAccountDetails(request.getCreditAccount());

        if (debit == null || credit == null) {
            throw new IllegalArgumentException("Invalid Account Number(s) provided.");
        }

        // Step 2: Create Dual Instructions (Debit & Credit)
        Instruction debitInstr = new Instruction(debit.getAccountNumber(), debit.getCurrency(),
                "DEBIT", request.getAmount(), debit.getBranch(), debit.getBank());

        Instruction creditInstr = new Instruction(credit.getAccountNumber(), credit.getCurrency(),
                "CREDIT", request.getAmount(), credit.getBranch(), credit.getBank());

        return List.of(debitInstr, creditInstr);
    }
}
