package com.example.accountservice.service;


import com.example.accountservice.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private AccountService accountService; // The dependency we want to mock

    @InjectMocks
    private PaymentService paymentService; // The service we are testing

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessPayment_Success() {
        // Arrange: Setup mock accounts
        Account dr = new Account("ACC1", "USD", "Bank A", "NYC","MS");
        Account cr = new Account("ACC2", "USD", "Bank B", "LON", "MS");

        when(accountService.getAccountDetails("ACC1")).thenReturn(dr);
        when(accountService.getAccountDetails("ACC2")).thenReturn(cr);

        PaymentRequest request = new PaymentRequest();
        request.setDebitAccount("ACC1");
        request.setCreditAccount("ACC2");
        request.setAmount(100.0);

        // Act
        List<Instruction> instructions = paymentService.createPaymentInstructions(request);

        // Assert
        assertEquals(2, instructions.size());
        assertEquals("DEBIT", instructions.get(0).getInstructionType());
        assertEquals("ACC1", instructions.get(0).getAccountNumber());
        verify(accountService, times(1)).getAccountDetails("ACC1");
    }

    @Test
    void testProcessPayment_InvalidAccount_ThrowsException() {
        // Arrange: Mock returning null for an account
        when(accountService.getAccountDetails("INVALID")).thenReturn(null);

        PaymentRequest request = new PaymentRequest();
        request.setDebitAccount("INVALID");
        request.setCreditAccount("ACC2");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.createPaymentInstructions(request);
        });
    }
}
