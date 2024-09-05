Scenario: Place a new order
    Given I set up the base URI
    When I place an order with userId 1, productId 1, quantity 2, and totalPrice 200
    Then the status code should be 201
    And the response should contain the userId 1