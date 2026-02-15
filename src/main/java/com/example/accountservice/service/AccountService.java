package com.example.accountservice.service;

import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountDetails(String accountNumber) {
        logger.info("Searching for Account Number: [{}]", accountNumber);

        return accountRepository.findById(accountNumber).orElseThrow(() -> {
            logger.error("Failed to find account details for account number: {}", accountNumber);
            return new AccountNotFoundException("No account found with account number: " + accountNumber);
        });
    }
}