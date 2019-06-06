#!/bin/bash
mvn clean install -DskipTests
REGPREFIX=improve
docker tag $(docker build --file=Dockerfile-ConfigServer --tag=${REGPREFIX}/config-server -q .) ${REGPREFIX}/config-server:$(date "+%Y%m%d-%H%M%S")
#docker build --file=Dockerfile-ConfigServer --tag=config-server:latest --rm=true .
#docker service create --name config-server -p 8888:8888 config-server:latest
