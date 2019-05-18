#!/bin/bash
mvn clean install
docker build --file=Dockerfile-EurekaServer --tag=eureka-server:latest --rm=true .
docker service create --name eureka-server --detach=false -p 8761:8761 eureka-server:latest
