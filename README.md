Microservice Sample - Island Reservations
==============

It uses three microservices:
- Reservation to process reservations on campsite.
- User to handle user data.
- Campsite to handle campsite data. (By the default there is only one campsite for reservation)

Technologies
------------

- Eureka for Lookup
- Hystrix is used for resilience.
- Zuul is used to route HTTP requests from the outside to the
  different services.
- Spring Cloud Config isn't used. It is disabled with
  spring.cloud.config.enabled=false in the bootstrap files.

Assumptions:
------------
Although we only have one place to camp on the island, I assumed that this could change in the future. Bearing in mind that in the near future you will probably have more than one campsite. Therefore, the solution will be more flexible to avoid future changes.
Also I assume that a user can reserve even though has already done it before. So a user can have more than one reservation.

Technical Staff:
------------

At the moment I use hsqldb as datastore. In future I will move to a Nosql or a Relational one. I have not decided yet. This depend on each microservice requirements.

How To Run
----------
Each Microservice has it own script names standalone.sh

cd eureka-server
sh standalone.sh
cd ..
cd zuul-server
sh standalone.sh
cd ..
cd user-ms
sh standalone.sh
cd ..
cd campsite-ms
sh standalone.sh
cd ..
cd registration-ms
sh standalone.sh
cd ..


Then, if all is ok, try to ping Eureka server -> http://localhost:8761/

Microservices Projects
-------------------

The servers for the infrastruture are pretty simple thanks to Spring Cloud:

- eureka-server: is the Eureka server for service discovery.
- zuul-server: is the Zuul server. It distributes the requests to the three microservices.

The microservices are: 
- campsite: is the application to take care of items.
- user: is responsible for customers.
- reservation: does reservation processing. It uses campsite and user microservices.


