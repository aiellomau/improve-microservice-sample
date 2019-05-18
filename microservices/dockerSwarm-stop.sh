#!/bin/bash

NUM_WORKERS=2

### Remove services
echo "Removing eureka-server service"
docker service rm eureka-server
echo "Removing config-server service"
docker service rm config-server
echo "Removing zuul-server service"
docker service rm zuul-server

### Unregister worker nodes
echo "Unregistering worker nodes"
for i in $(seq "${NUM_WORKERS}"); do
	docker --host=localhost:${i}2375 swarm leave
done

### Remove worker nodes
echo "Removing worker nodes"
docker rm -f $(docker ps -a -q --filter ancestor=docker:${DOCKER_VERSION} --format="")

### Leave Swarm mode
echo "Leaving Swarn mode"
docker swarm leave --force
