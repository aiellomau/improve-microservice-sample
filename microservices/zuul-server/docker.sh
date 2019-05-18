#!/bin/bash
mvn clean install
docker build --file=Dockerfile-ZuulServer --tag=zuul-server:latest --rm=true .
