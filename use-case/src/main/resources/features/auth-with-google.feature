Feature: Login via Google

  Scenario: Authenticate with already existing username
    Given a user "badro" with email "badr@gmail.com", provider user id "123456789" and has an account status of "ACTIVE" exists in the database
    When the user "badro" with email "badr@gmail.com" and provider user id "123456789" tries to authenticate with id token "789456123"
    Then the authentication is granted

  @Failed
  Scenario: Fail authentication for a banned account
    Given a user "badro" with email "badr@gmail.com", provider user id "123456789" and has an account status of "BANNED" exists in the database
    When the user "badro" with email "badr@gmail.com" and provider user id "123456789" tries to authenticate with id token "789456123"
    Then the authentication is denied
    And the user must be informed of the violation of "br.0004.auth.user"