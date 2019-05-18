#!/bin/bash
mvn clean install
docker build --file=Dockerfile-ZuulServer --tag=zuul-server:latest --rm=true .
docker service create --name zuul-server --detach=false -p 8762:8762 zuul-server:latest
