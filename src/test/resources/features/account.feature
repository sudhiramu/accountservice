Feature: Account Service
  As a user of the account service
  I want to look up account details by account number
  So that I can retrieve account information

  Scenario: Successfully retrieve an existing account
    Given an account with number "1001" exists in the system
    When I request the account details for account number "1001"
    Then the response status should be 200
    And the response should contain account holder name "Sudhir Tiwari"
    And the response should contain currency "USD"
    And the response should contain branch "New York"

  Scenario: Attempt to retrieve a non-existent account
    When I request the account details for account number "9999"
    Then the response status should be 404
    And the response should contain error message "No account found with account number: 9999"
