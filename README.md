App will help to manipulate CRUD operations for your favourite cryptocurrency .

To save entity(name, amount, wallet) :
POST http://localhost:8050/api/crypto/entities

To get all entities : 
GET http://localhost:8050/api/crypto/show-entities

To get entities by id :
GET http://localhost:8050/api/crypto/entities/{id}

To update existing entity(name, amount, wallet):
PUT http://localhost:8050/api/crypto/entities/{id}

To delete entity by id :
DELETE http://localhost:8050/api/crypto/entities/{id}

Stack: Spring-boot + Spring Data JPA + MySQL

MySQL DB user: root/rootroot

Run these commands from the app folder to start the app:

mvnw clean install

docker build -t onoff-spring-boot .

docker-compose up