Feature: New accounts registration test

  Background:
    * url baseUrl

  Scenario: Valid payload must return success on registration

    Given path '/accounts'
    And request { document: '1234567899', balance: '500' }
    When method POST
    Then status 201
    And match response ==
                            """
                              {
                                  "id": #number,
                                  "document": "1234567899",
                                  "balance": 500
                              }
                            """

  Scenario: Try add a already registered user must return a error

    Given path '/accounts'
    And request { document: '1234567811', balance: '500' }
    When method POST
    Then status 201

    Given path '/accounts'
    And request { document: '1234567811', balance: '500' }
    When method POST
    Then status 409
    And match response ==
                            """
                              [
                                  {
                                      "code": "1004",
                                      "message": "Account already exist"
                                  }
                              ]
                            """

  Scenario: Try add a account without document must return a error

    Given path '/accounts'
    And request { balance: '500' }
    When method POST
    Then status 400
    And match response ==
                          """
                            [
                                {
                                    "code": "document",
                                    "message": "must not be blank"
                                }
                            ]
                          """

  Scenario: Try add a account without balance must return a error

    Given path '/accounts'
    And request { document: '1234567822', }
    When method POST
    Then status 400
    And match response ==
                          """
                            [
                                {
                                    "code": "balance",
                                    "message": "must not be null"
                                }
                            ]
                          """