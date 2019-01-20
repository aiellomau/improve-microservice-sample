Upgrade Microservice Sample
==============

It uses three microservices:
- Reservation to process reservations on campsite.
- User to handle user data.
- Campsite to handle campsite data. (By the fault there is only one campsite for reservation)

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
Even Though that we have just only campsite on the island, I assumed that this could be changed in future. Having in mind that in near future will probably have more than one campsite, the solution will be more flexible in order to prevent future changes.

Also I assume that a user can reserve eventhough has already done it before. So a user can have more than one reservation.

Technical Decitions:
------------

I user hsqldb as datastore for now.

How To Run
----------

The demo can be run with [Docker Machine and Docker
Compose](docker/README.md).

[How to run](HOW-TO-RUN.md) includes more details.


Remarks on the Code
-------------------

The servers for the infrastruture are pretty simple thanks to Spring Cloud:

- microservice-demo-eureka is the Eureka server for service discovery.
- microservice-demo-zuul is the Zuul server. It distributes the requests to the three microservices.

The microservices are: 
- microservice-demo-catalog is the application to take care of items.
- microserivce-demo-customer is responsible for customers.
- microservice-demo-order does order processing. It uses microservice-demo-catalog and microservice-demo-customer. Ribbon is used for load balancing and Hystrix for resilience.


The microservices have an Java main application in src/test/java to run them stand alone. microservice-demo-order uses a stub for the other services then. Also there are tests that use customer driven contracts. That is why it is ensured that the services provide the correct interface. These CDC tests are used in microservice-demo-order to verify the stubs. In microserivce-demo-customer and microserivce-demo-catalog they are used to verify the implemented REST services.
