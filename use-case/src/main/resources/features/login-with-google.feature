Feature: Login via Google

  @Success
  Scenario: Login user
    Given a user "badro" with email "badr@gmail.com", provider user id "123456789" and has an account status of "ACTIVE" exists in the database
    When the user "badro" with email "badr@gmail.com" and provider user id "123456789" tries to login with id token "789456123"
    Then the authentification request is granted

  @Failed
  Scenario: Login a banned account
    Given a user "badro" with email "badr@gmail.com", provider user id "123456789" and has an account status of "BANNED" exists in the database
    When the user "badro" with email "badr@gmail.com" and provider user id "123456789" tries to login with id token "789456123"
    Then the authentication is denied
    And the user must be informed of the violation of "br.0004.auth.user"

  @s
  Scenario: Login new user
    Given a user "badro" with email "badr@gmail.com", provider user id "123456789" and has an account status of "ACTIVE" exists in the database
    When the user with google username "omar", email "omar@gmail.com" and provider user id "987654321" tries to login with id token "012345678"
    Then the user is required to register