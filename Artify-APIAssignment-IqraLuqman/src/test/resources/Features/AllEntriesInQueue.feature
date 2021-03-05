@allEntries
Feature: All entries and wait time

  Scenario: To see all entries in the queue with the approximate wait time
    Given User set donut service api endpoint
    When User set request HEADER
    And Send a GET HTTP request
    Then User receive status code 200 OK
    And response includes all order entries and wait time.
