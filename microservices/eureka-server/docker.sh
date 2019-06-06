#!/bin/bash

mvn clean install -DskipTests
REGPREFIX=improve
docker tag $(docker build --file=Dockerfile-EurekaServer --tag=${REGPREFIX}/eureka-server -q .) ${REGPREFIX}/eureka-server:$(date "+%Y%m%d-%H%M%S")
#docker service create --name eureka-server --detach=false -p 8761:8761 eureka-server:latest
