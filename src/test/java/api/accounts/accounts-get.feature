Feature: Search accounts test

  Background:
    * url baseUrl

  Scenario: Try search an existent account must return account on response body

    Given path '/accounts'
    And request { document: '1134567811', balance: '900.60' }
    When method POST
    Then status 201
    * def id = get response.id

    Given path '/accounts/' + id
    When method GET
    Then status 200
    And match response ==
                            """
                              {
                                  "id": #number,
                                  "document": "1134567811",
                                  "balance": 900.60
                              }
                            """

  Scenario: Try search an inexistent account must return 404

    Given path '/accounts/25899'
    When method GET
    Then status 404