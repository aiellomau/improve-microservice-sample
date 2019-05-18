#!/bin/bash
mvn clean install
docker build --file=Dockerfile-ConfigServer --tag=config-server:latest --rm=true .
docker service create --name config-server --detach=false -p 8888:8888 config-server:latest
