#!/bin/bash

docker service scale eureka-server=2 --detach=true
docker service scale config-server=2 --detach=true
docker service scale zuul-server=2 --detach=true
