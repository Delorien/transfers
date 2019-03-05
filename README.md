# Transfer
### What is it
Simple MVP REST API for money transfers between accounts.

### How to try the API
You can import the Postman collection by here

[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/7d541fbb04dc588c1c2b)

Or import the follow URL directly in Postman https://www.getpostman.com/collections/7d541fbb04dc588c1c2b

### What is being used in this project

* Run with __Java 8__
*  __[Jetty](https://www.eclipse.org/jetty/)__ - embedded server / servlet container
* __[Jersey](https://jersey.github.io/)__ - JAX-RS implementation for REST service development
* __[HK2](https://javaee.github.io/hk2/)__ - dependency injection framework
* __[H2](http://www.h2database.com/html/main.html)__ - embedded relational database
* __[Jdbi](http://jdbi.org/)__ - to access the relational database
* __[ModelMapper](http://modelmapper.org/)__ & __[Lombok](https://projectlombok.org/)__ - to spicing up the code
* __[Shadow](https://github.com/johnrengelman/shadow)__ - Gradle plugin for creating fat/uber JARs 
* __[Karate](https://github.com/intuit/karate)__ - Cucumber based tool for API test-automation

### How test, pack and run this project
#### Build and Run
* To __build__ and generate a __package__ use the Gradle shadowJar task
```bash
./gradlew clean shadowJar
```
* To __run__ the generated jar use the follow command
```bash
java -jar build/libs/transfers.jar
```

#### Testing
* To run the __tests__ execute the Gradle task
```bash
./gradlew clean test --info
```
At the end, tests will generate a report on *transfers/build/reports/tests/test/index.html*
