Feature: Effect transfers test

  Background:
    * url baseUrl

  Scenario: A valid transfer payload must return success with transfer body response

    Given path '/accounts'
    And request { document: '1122334455', balance: '400.00' }
    When method POST
    Then status 201
    * def originId = get response.id

    Given path '/accounts'
    And request { document: '1122334466', balance: '0.00' }
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
    And match response ==
                            """
                              {
                                  "id": #number,
                                  "origin": {
                                      "id": "#(originId)",
                                      "document": "1122334455",
                                      "balance": 100.00
                                  },
                                  "receiver": {
                                      "id": #(receiverId),
                                      "document": "1122334466",
                                      "balance": 300.00
                                  },
                                  "amount": 300.00,
                                  "date": #number
                              }
                            """

  Scenario: A transfer payload with a inexistent account must return a error on response
    Given path '/transfers'
    And request
      """
        {
            "origin": {
                "id": "100"
            },
            "receiver": {
                "id": "1"
            },
            "amount": "300.00"
        }
      """
    When method POST
    Then status 404
    And match response ==
                            """
                              [
                                  {
                                      "code": "1001",
                                      "message": "Account not found"
                                  }
                              ]
                            """

  Scenario: A valid transfer from an account without enough balance must return a error on response

    Given path '/accounts'
    And request { document: '9998888', balance: '100.00' }
    When method POST
    Then status 201
    * def originId = get response.id

    Given path '/accounts'
    And request { document: '55556666', balance: '0.00' }
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
    Then status 400
    And match response ==
                            """
                              [
                                  {
                                      "code": "1003",
                                      "message": "Insufficient balance"
                                  }
                              ]
                            """


  Scenario: A transfer payload without amount must return a error on response
    Given path '/transfers'
    And request
      """
        {
            "origin": {
                "id": "100"
            },
            "receiver": {
                "id": "1"
            }
        }
      """
    When method POST
    Then status 400
    And match response ==
                            """
                              [
                                  {
                                      "code": "amount",
                                      "message": "must not be null"
                                  }
                              ]
                            """

  Scenario: A transfer payload without origin id must return a error on response
    Given path '/transfers'
    And request
      """
        {
            "origin": {

            },
            "receiver": {
                "id": "1"
            },
            "amount": "300.00"
        }
      """
    When method POST
    Then status 400
    And match response ==
                            """
                              [
                                  {
                                      "code": "origin.id",
                                      "message": "must not be null"
                                  }
                              ]
                            """

  Scenario: A transfer payload without origin must return a error on response
    Given path '/transfers'
    And request
      """
        {
            "receiver": {
                "id": "1"
            },
            "amount": "300.00"
        }
      """
    When method POST
    Then status 400
    And match response ==
                            """
                              [
                                  {
                                      "code": "origin",
                                      "message": "must not be null"
                                  }
                              ]
                            """

  Scenario: A transfer payload without receiver id must return a error on response
    Given path '/transfers'
    And request
      """
        {
            "origin": {
                "id": "1"
            },
            "receiver": {

            },
            "amount": "300.00"
        }
      """
    When method POST
    Then status 400
    And match response ==
                            """
                              [
                                  {
                                      "code": "receiver.id",
                                      "message": "must not be null"
                                  }
                              ]
                            """

  Scenario: A transfer payload without receiver must return a error on response
    Given path '/transfers'
    And request
      """
        {
            "origin": {
                "id": "1"
            },
            "amount": "300.00"
        }
      """
    When method POST
    Then status 400
    And match response ==
                            """
                              [
                                  {
                                      "code": "receiver",
                                      "message": "must not be null"
                                  }
                              ]
                            """

  Scenario: A transfer payload with balance smaller than zero must return a error on response

    Given path '/accounts'
    And request { document: '99998888', balance: '400.00' }
    When method POST
    Then status 201
    * def originId = get response.id

    Given path '/accounts'
    And request { document: '77775555', balance: '0.00' }
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
            "amount": "00.00"
        }
      """


    When method POST
    Then status 400
    And match response ==
                            """
                              [
                                  {
                                      "code": "amount",
                                      "message": "must be greater than 0"
                                  }
                              ]
                            """