@nextDelivery
Feature: Next delivery

  Scenario: To retrieve next delivery that should be placed in cart
    Given User set donut service api endpoint
    When User set request HEADER
    And Send a GET HTTP request with path parameter nextdelivery
    Then User receive status code 200 OK
    And Response include next delivery details
