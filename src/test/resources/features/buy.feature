Feature: Buy products
    As a customer
    I want to buy products

Background:
    Given the store is ready to service customers
    And a product "Bread" with price 20.50 and stock of 5 exists
    And a product "Jam" with price 80.00 and stock of 10 exists
    And a product "Truffle Sausage" with price 250.00 and stock of 10 exists

Scenario: Buy one product
    When I buy "Bread" with quantity 2
    Then total should be 41.00

Scenario: Buy multiple products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    Then total should be 121.00
Scenario: Buy three products
    When I buy "Bread" with quantity 1
    And I buy "Jam" with quantity 1
    And I buy "Truffle Sausage" with quantity 1
    Then total should be 350.50
    And total of "Bread" in stock is 4
    And total of "Jam" in stock is 9
    And total of "Truffle Sausage" in stock is 9
Scenario: Buy more than available stock
    When I buy "Bread" with quantity 6
    Then error should be thrown
    And total of "Bread" in stock is 5