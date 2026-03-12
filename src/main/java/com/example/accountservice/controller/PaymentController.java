package com.example.accountservice.controller;

import com.example.accountservice.model.*;

import com.example.accountservice.service.PaymentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payment Instruction", description = "Create and process payment instructions")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/instruction")
    public ResponseEntity<?> sendInstruction(@RequestBody PaymentRequest request) {
        try {
            List<Instruction> instructions = paymentService.createPaymentInstructions(request);
            return ResponseEntity.ok(instructions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Payment Failed: " + e.getMessage());
        }
    }
}