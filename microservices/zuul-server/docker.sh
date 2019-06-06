#!/bin/bash
mvn clean install -DskipTests
REGPREFIX=improve
docker tag $(docker build --file=Dockerfile-ZuulServer --tag=${REGPREFIX}/zuul-server -q .) ${REGPREFIX}/zuul-server:$(date "+%Y%m%d-%H%M%S")
#docker build --file=Dockerfile-ZuulServer --tag=zuul-server:latest --rm=true .
#docker service create --name zuul-server -p 8762:8762 zuul-server:latest
