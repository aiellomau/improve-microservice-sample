Microservice Sample - Island Reservations
==============

It uses three microservices:
- Reservation to process reservations on campsite.
- User to handle user data.
- Campsite to handle campsite data. (By the default there is only one campsite for reservation)

Technologies
------------

- Eureka for Lookup.
- Hystrix is used for resilience.
- Zuul is used to route HTTP requests from the outside to the
  different services.
- Spring Cloud Config.
- Spring Boot Admin. Use as a dashboard of all microservices.
- Feign - For Rest services connections withing microservices. Avoid Rest API implementation staff.
- RabbitMQ - For exchanges messages between microservices.

Assumptions:
------------
Although we only have one place to camp on the island, I assumed that this could change in the future. Bearing in mind that in the near future you will probably have more than one campsite. Therefore, the solution will be more flexible to avoid future changes.
Also I assume that a user can reserve even though has already done it before. So a user can have more than one reservation.

Technical Staff:
------------

At the moment I use hsqldb as datastore. In future I will move to a Nosql or a Relational one. I have not decided yet. This depend on each microservice requirements.

How To Run
----------
Each Microservice has it own script names standalone.sh. Is recomended to have several terminals to run all.
```sh
# Start Discovery
$ cd eureka-server
$ sh standalone.sh
```
```sh
# Config Server
$ cd config-server
$ sh standalone.sh
```
```sh
# Dashboard
$ cd dashboard-server
$ sh standalone.sh
```
```sh
# Api Gateway
$ cd zuul-server
$ sh standalone.sh
```
```sh
# Start User Microservice
$ cd user-ms
$ sh standalone.sh
```
```sh
# Start Campsite Microservice
$ cd campsite-ms
$ sh standalone.sh
```
```sh
# Start Reservation Microservice
$ cd registration-ms
$ sh standalone.sh
```

Then, if all is ok, try to ping Eureka server -> http://localhost:8761/

Read more on: https://github.com/aiellomau/improve-microservice-sample/tree/master/microservices

Microservices Projects
-------------------

The servers for the infrastruture are pretty simple thanks to Spring Cloud:

- eureka-server: is the Eureka server for service discovery.
- config-server: is the Config server. It hold all properties values for each ms. All properties are hosting on GitHub: https://github.com/aiellomau/improve-microservice-configserver
- dashboard-server: Monitor all microservices instances.
- zuul-server: is the Zuul server. Distributes the requests to the three microservices.

The microservices are: 
- campsite: is the application to take care of items.
- user: is responsible for customers.
- reservation: does reservation processing. It uses campsite and user microservices.


RabbitMQ instance (Just for testing comunication between microservices)
-------------------

```sh
docker pull rabbitmq:3-management
```
```sh
docker run -d --hostname my-rabbit --name some-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
```



![Microservices](https://raw.githubusercontent.com/aiellomau/improve-microservice-sample/master/docs/Reservation%20Island%20-%20Microservices.png)

Future enhancements
-------------------
> Add Docker-Swarn / Kubernetes.
