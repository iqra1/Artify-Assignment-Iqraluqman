#Author: iqra_luqman@hotmail.com

@addingItem
Feature: Adding donut item into the queue

  Scenario: Adding donut items into the queue with two parameters, ID of the client and the quantity
    Given User set donut service api endpoint
    When User set request HEADER
    And User set request BODY
    And Send a POST HTTP request
    Then User receive status code 201
    And Response include message resource created
