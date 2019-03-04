Feature: Search accounts test

  Background:
    * url baseUrl

  Scenario: Try search an existent transfer must return a transfers on response body

    Given path '/accounts'
    And request { document: '55557888', balance: '400.00' }
    When method POST
    Then status 201
    * def originId = get response.id

    Given path '/accounts'
    And request { document: '55557889', balance: '0.00' }
    When method POST
    Then status 201
    * def receiverId = get response.id

    Given path '/transfers'
    And request
      """
        {
            "origin": {
                "id": "#(originId)"
            },
            "receiver": {
                "id": "#(receiverId)"
            },
            "amount": "300.00"
        }
      """
    When method POST
    Then status 201
    * def id = get response.id

    Given path '/transfers/' + id
    When method GET
    Then status 200
    And match response ==
                            """
                              {
                                  "id": #number,
                                  "origin": {
                                      "id": "#(originId)"
                                  },
                                  "receiver": {
                                      "id": "#(receiverId)"
                                  },
                                  "amount": 300.00,
                                  "date": #number
                              }
                            """

  Scenario: Try search an inexistent transfer must return 404

    Given path '/transfers/25899'
    When method GET
    Then status 404