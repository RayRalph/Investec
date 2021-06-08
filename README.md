# REST API test
##GOAL
The goal of this assessment is to gage the assessment takers knowledge and understanding of endpoints.
## Prerequisites
1. java 8
1. Postman
1. Maven

### Technologies
* [Maven](https://maven.apache.org/) - Dependency management
* [Spring Boot](https://projects.spring.io/spring-boot/) - Quickly create stand-alone, production-grade Spring based Applications
* [H2](http://www.h2database.com/html/main.html) - In-memory database of choice
* Swagger

#Running the Application

Run the following commands from the base project directory
* To build the project:
```
mvn clean install
```
* To run the project:
```
mvn spring-boot:run
```

### Listing port
This application is configured to run on http://localhost:8081, however, you can change it in the application.properties file.

## To open in the browser: 
###For Swagger
```
http://localhost:8081/Assessment/swagger-ui.html
```
###For the H2 Database
```
http://localhost:8081/Assessment/h2-console
```



