Feature: Register with Google

  @Success
  Scenario: Register new user
    Given a user "badro" with email "badr@gmail.com", provider user id "123456789" and has an account status of "ACTIVE" exists in the database
    When the user with google username "omar", email "omar@gmail.com" and provider user id "987654321" tries to login with id token "012345678"
    And the user with username "omar", email "omar@gmail.com" and provider user id "987654321" tries to register
    Then the authentification request is granted

  @Failed
  Scenario: Register with already existing username
    Given a user "badro" with email "badr@gmail.com", provider user id "123456789" and has an account status of "ACTIVE" exists in the database
    When the user with google username "badro", email "omar@gmail.com" and provider user id "987654321" tries to login with id token "012345678"
    And the user with username "badro", email "omar@gmail.com" and provider user id "987654321" tries to register
    Then the authentication is denied
    And the user must be informed of the violation of "br.0001.auth.user"