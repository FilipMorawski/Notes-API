# Checkout Component

Backend API for managing notes. It provides functionalities like creating, editing, removing and obtaining specified note or list of all notes stored in database. It keeps notes state in chosen type of SQL database.

## Requirements

* Java 1.8
* SQL database of any kind
* Maven for building app

## Documentation

Documentation is provided by Swagger UI, please visit link below. Documentation contains all example usages.

* [REST Api Documentation](https://app.swaggerhub.com/apis/Filip-Morawski/NotesAPI/1.0) - SwaggerHub

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
Installation requires Maven and Git.

### Get Repository

```
git clone https://github.com/FilipMorawski/NotesAPI
```
### Set Database

Script that will set up the database is located in src/main/resources in schema.sql file. Script will be run autoumaticly on start of the app by Spring Boot.
JDBC driver and information about used database and connection is placed in src/main/resources/application.properties file and by  default is setted on MySQL and developer database connection. Please enter your own configuration. Keep in mind that 
```
spring.jpa.hibernate.ddl-auto
```
property must be set to "none"

## Testing and Running

Application is built with Maven, so you can use these commands to test and install NotesAPI

### Test

For testing purposes app uses H2 in-memory database, so it is not necessary to have own database server for running tests.

```
mvn test
```

### Installing

```
mvn clean install
```
After that, user can execute jar. file located in target directory


## Built With

* SpringBoot
* Hibernate
* Swagger
* MySQL
* H2 Database
* Mockito
* jUnit
* Maven

