Feature: Login with valid credentials

  #@sanity @regression
  #Scenario: Succcessful login with valid credentials
  # Given the user navigates to login page
  # When the user enter valid email "billa123@gmail.com" and password "billa@123"
  #And the user clicked the login button
  #Then the user should be redirected to Myaccount page
  
  
  
  
  @regression
  Scenario Outline: Succcessful login with valid credentials
    Given the user navigates to login page
    When the user enter valid email "<email>" and password "<password>"
    And the user clicked the login button
    Then the user should be redirected to Myaccount page

    Examples: 
      | email              | password  |
      | billa123@gmail.com | billa@123 |
      | sabar123@gmail.com | sabar@123 |
