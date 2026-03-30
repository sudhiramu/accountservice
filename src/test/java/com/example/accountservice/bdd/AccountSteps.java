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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

public class AccountSteps {

    @Autowired
    private MockMvc mockMvc;

    private MvcResult mvcResult;

    @Given("an account with number {string} exists in the system")
    public void anAccountWithNumberExistsInTheSystem(String accountNumber) throws Exception {
        mvcResult = mockMvc.perform(get("/api/accounts/" + accountNumber))
                .andExpect(status().isOk())
                .andReturn();
    }

    @When("I request the account details for account number {string}")
    public void iRequestTheAccountDetailsForAccountNumber(String accountNumber) throws Exception {
        mvcResult = mockMvc.perform(get("/api/accounts/" + accountNumber)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int expectedStatus) {
        assertEquals(expectedStatus, mvcResult.getResponse().getStatus());
    }

    @And("the response should contain account holder name {string}")
    public void theResponseShouldContainAccountHolderName(String expectedName) throws Exception {
        String body = mvcResult.getResponse().getContentAsString();
        assertTrue(body.contains(expectedName), "Expected body to contain: " + expectedName);
    }

    @And("the response should contain currency {string}")
    public void theResponseShouldContainCurrency(String expectedCurrency) throws Exception {
        String body = mvcResult.getResponse().getContentAsString();
        assertTrue(body.contains(expectedCurrency), "Expected body to contain: " + expectedCurrency);
    }

    @And("the response should contain branch {string}")
    public void theResponseShouldContainBranch(String expectedBranch) throws Exception {
        String body = mvcResult.getResponse().getContentAsString();
        assertTrue(body.contains(expectedBranch), "Expected body to contain: " + expectedBranch);
    }

    @And("the response should contain error message {string}")
    public void theResponseShouldContainErrorMessage(String expectedMessage) throws Exception {
        String body = mvcResult.getResponse().getContentAsString();
        assertTrue(body.contains(expectedMessage), "Expected body to contain: " + expectedMessage);
    }
}
