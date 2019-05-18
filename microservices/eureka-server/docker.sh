#!/bin/bash
mvn clean install
docker build --file=Dockerfile-EurekaServer --tag=eureka-server:latest --rm=true .
