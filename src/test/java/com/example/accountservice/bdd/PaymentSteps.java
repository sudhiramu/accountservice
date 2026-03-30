package com.example.accountservice.bdd;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentSteps {

    @Autowired
    private MockMvc mockMvc;

    private MvcResult mvcResult;

    @Given("accounts {string} and {string} exist in the system")
    public void accountsExistInTheSystem(String account1, String account2) throws Exception {
        mockMvc.perform(get("/api/accounts/" + account1)).andExpect(status().isOk());
        mockMvc.perform(get("/api/accounts/" + account2)).andExpect(status().isOk());
    }

    @When("I submit a payment request to debit {string} and credit {string} with amount {double}")
    public void iSubmitAPaymentRequest(String debitAccount, String creditAccount, double amount) throws Exception {
        String jsonBody = String.format(
                "{\"debitAccount\":\"%s\",\"creditAccount\":\"%s\",\"amount\":%s}",
                debitAccount, creditAccount, amount);

        mvcResult = mockMvc.perform(post("/api/payments/instruction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andReturn();
    }

    @Then("the payment response status should be {int}")
    public void thePaymentResponseStatusShouldBe(int expectedStatus) {
        assertEquals(expectedStatus, mvcResult.getResponse().getStatus());
    }

    @And("the response should contain {int} instructions")
    public void theResponseShouldContainInstructions(int expectedCount) throws Exception {
        String body = mvcResult.getResponse().getContentAsString();
        // Count JSON objects by counting occurrences of "instructionType"
        long count = 0;
        int index = 0;
        while ((index = body.indexOf("instructionType", index)) != -1) {
            count++;
            index++;
        }
        assertEquals(expectedCount, count);
    }

    @And("the first instruction should be a {string} for account {string}")
    public void theFirstInstructionShouldBe(String type, String accountNumber) throws Exception {
        String body = mvcResult.getResponse().getContentAsString();
        assertTrue(body.contains("\"instructionType\":\"" + type + "\""),
                "Expected instruction type: " + type);
        assertTrue(body.contains("\"accountNumber\":\"" + accountNumber + "\""),
                "Expected account number: " + accountNumber);
    }

    @And("the second instruction should be a {string} for account {string}")
    public void theSecondInstructionShouldBe(String type, String accountNumber) throws Exception {
        String body = mvcResult.getResponse().getContentAsString();
        assertTrue(body.contains("\"instructionType\":\"" + type + "\""),
                "Expected instruction type: " + type);
        assertTrue(body.contains("\"accountNumber\":\"" + accountNumber + "\""),
                "Expected account number: " + accountNumber);
    }
}
