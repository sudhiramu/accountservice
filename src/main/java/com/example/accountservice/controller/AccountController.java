package com.example.accountservice.controller;


import com.example.accountservice.model.Account;
import com.example.accountservice.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@Tag(name = "Account Service", description = "REST APIs for Account Operations")
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @Operation(summary = "Get Account Details", description = "Fetches the details of an account using the account number.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved account details",
                    content = @Content(schema = @Schema(implementation = Account.class))),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(
            @Parameter(description = "The 4-digit account number range(1001-1004)", required = true)
            @PathVariable String accountNumber) {

        logger.info("Received API request to fetch account: {}", accountNumber);
        Account account = accountService.getAccountDetails(accountNumber);
        return ResponseEntity.ok(account);
    }
}