@cancelOrder
Feature: Order cancellation

  Scenario: To verify order cancellation using ID of the client
    Given User set donut service api endpoint
    When User set request HEADER
    And User set request BODY with status cancel
    And Send a PATCH HTTP request with path parameter client ID
    Then User receive status code 200 OK
    And Response should include the order status as cancelled
