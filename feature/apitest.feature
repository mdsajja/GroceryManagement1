Feature: API Testing for User, Product, and Order Management

  Scenario: Register a new user
    Given I set up the base URI
    When I register a new user with username "testuser", password "password", and email "test@example.com"
    Then the status code should be 201
    And the response should contain the username "testuser"

  Scenario: Login a user
    Given I set up the base URI
    When I login with username "testuser" and password "password"
    Then the status code should be 200
    And the response message should be "Login successful"

  Scenario: Add a new product
    Given I set up the base URI
    When I add a product with name "Product1", category "Category1", price 100, and stockQuantity 10
    Then the status code should be 201
    And the response should contain the product name "Product1"

  Scenario: Search for a product by name
    Given I set up the base URI
    When I search for a product by name "Product4"
    Then the status code should be 200
    And the response should contain the product name "Product4"

  Scenario: Place a new order
    Given I set up the base URI
    When I place an order with userId 1, productId 1, quantity 2, and totalPrice 200
    Then the status code should be 201
    And the response should contain the userId 1

  Scenario: Retrieve order details
    Given I set up the base URI
    When I retrieve the order with orderId 1
    Then the status code should be 200
    And the response should contain the orderId 1

  Scenario: Update user profile
    Given I set up the base URI
    When I update the user with userId 12 to username "updateduser" and email "updated@example.com"
    Then the status code should be 200
    And the response should contain the updated username "updateduser"

  Scenario: Delete a user account
    Given I set up the base URI
    When I delete the user with userId 11
    Then the status code should be 204

  Scenario: Search for a product by name
    Given I set up the base URI
    When I search for a product by name "Product1"
    Then the status code should be 200
    And the response should contain the product name "Product1"

  Scenario: Filter products by category
    Given I set up the base URI
    When I filter products by category "Category1"
    Then the status code should be 200
    And the response should contain the category "Category1"
