# CS348-Project-Backend

## Description

This backend service is used to interact with the MySQL database.

## Prerequisites

- MySQL Database Server running on local device
- Java Development Kit (JDK)
- IntelliJ IDEA or any other Java IDE
- Maven or Maven Wrapper installed on local device
- Ensure that port 8080 isn't in use

## Usage
If maven is installed on local device 
1. ```mvn clean install```
2. ```mvn spring-boot:run``` or run the application directly in IDE

### Verification
To verify that the server is up and running. Open `http://localhost:8080/healthCheck` on a browser. Should be able to see something like "CS348 service is up and running! 2024-05-31 23:29:13 UTC"

### Interaction with Database
Ensure Maven is rebuild after clean install.
<br> Configure the file application.properties based on individual database settings, update the following field:
1. ```spring.datasource.url``` : add the name of database in MySQLWorkbench after "3306/", i.e ```jdbc:mysql://localhost:3306/my_database_name```
2. ```spring.datasource.username``` : username, i.e ```root```
3. ```spring.datasource.password ```: personal password
<br> Internal server error implies wrong configuration on this file
  

