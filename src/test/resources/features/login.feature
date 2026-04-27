Feature: Login functionality
@login
  Scenario: Valid Login
    Given user is on login page
    Then pdf validation
    When user enters username "bhanuprakash"
    And user enters password "gibbs_123"
    And user clicks login button
    Then user should see home page


@updatedLogin
  Scenario Outline: Login with multiple users
    Given user is on login page
    Then pdf_validation
    When user enters username "<username>"
    And user enters password "<password>"
    And user clicks login button
    Then user should see home page


    Examples:
      | username| password |
      | bhanu  | prakash_1234 |
