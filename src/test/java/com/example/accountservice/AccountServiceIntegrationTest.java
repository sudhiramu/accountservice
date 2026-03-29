package com.example.accountservice;

import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // Rolls back changes after each test to keep H2 clean
class AccountServiceIntegrationTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testServiceWithRealDatabase() {
        // Arrange
        Account acc = new Account("REAL_ACC", "EUR", "Deutsche Bank", "Berlin", "Morgan");
        accountRepository.save(acc);

        // Act
        Account result = accountService.getAccountDetails("REAL_ACC");

        // Assert
        assertNotNull(result);
        assertEquals("Berlin", result.getBranch());
    }
}
