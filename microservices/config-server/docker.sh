#!/bin/bash
mvn clean install
docker build --file=Dockerfile-ConfigServer --tag=config-server:latest --rm=true .
