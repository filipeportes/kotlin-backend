# kotlin-backend
Kotlin backend demo project using spring boot web for REST API, liquidbase to create a fresh H2 database, 
 spring-jpa to interact with the data and camel for integrations.
 
## Getting Started

`mvn clean package`

this will package and execute tests 

`mvn spring-boot:run`

this will start the application and initialize the h2 database with the contact table 

## Contact Rest API
GET localhost:8080/contacts to list all
GET localhost:8080/contact/{id} to list one
POST localhost:8080/contact to add one
DELETE localhost:8080/contact/{id} to delete one

adding a new contact: 

```$bash
curl -X POST -H "content-type:application/json;" -d '{"name":"Filipe","email":"filipe@email.com","phone":"123456","creationDate":"2020-01-01"}' localhost:8080/contact
```

deleting a contact: 

```$bash
curl -X DELETE localhost:8080/contact/1
```

## Contacts from File with Camel

add a file with any name in the folder `/tmp/contacts` with the content in this format:

```json
[
{"name":"First Contact","email":"first@email.com","phone":"123456789", "creationDate":"2020-01-01"},
{"name":"Second Contact","email":"second@email.com","phone":"123456789","creationDate":"2020-01-01"}
]
```

processed files will be moved to `/tmp/contacts/.done`  
failed files will be moved to `/tmp/contacts/.failed`

