package com.example.accountservice.service;


import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    private Account mockAccount;

    @BeforeEach
    void setUp() {
        mockAccount = new Account("ACC123", "USD", "Chase", "Manhattan", "Morgan");
    }

    @Test
    void testGetAccountDetails_Success() {
        // Arrange
        when(accountRepository.findById("ACC123")).thenReturn(Optional.of(mockAccount));

        // Act
        Account result = accountService.getAccountDetails("ACC123");

        // Assert
        assertNotNull(result);
        assertEquals("ACC123", result.getAccountNumber());
        verify(accountRepository, times(1)).findById("ACC123");
    }

    @Test
    void testGetAccountDetails_NotFound_ThrowsException() {
        // Arrange
        when(accountRepository.findById("EMPTY")).thenReturn(Optional.empty());

        // Act & Assert
        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class, () -> {
            accountService.getAccountDetails("EMPTY");
        });

        assertEquals("No account found with account number: EMPTY", exception.getMessage());
    }
}