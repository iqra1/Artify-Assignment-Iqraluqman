@QueuePositionAndWaitTime
Feature: Order position in queue and wait time

  Scenario: To check order position and approximate wait time
    Given User set donut service api endpoint
    When User set request HEADER
    And Send a GET HTTP request with path parameter client ID
    Then User receive status code 200 OK
    And User receive valid response that contains queue position number and wait time.
