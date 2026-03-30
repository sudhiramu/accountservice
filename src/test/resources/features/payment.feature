Feature: Payment Instruction Service
  As a user of the payment service
  I want to create payment instructions
  So that funds can be transferred between accounts

  Scenario: Successfully create payment instructions
    Given accounts "1001" and "1002" exist in the system
    When I submit a payment request to debit "1001" and credit "1002" with amount 500.00
    Then the payment response status should be 200
    And the response should contain 2 instructions
    And the first instruction should be a "DEBIT" for account "1001"
    And the second instruction should be a "CREDIT" for account "1002"

  Scenario: Payment with an invalid account fails
    When I submit a payment request to debit "9999" and credit "1002" with amount 100.00
    Then the payment response status should be 400
