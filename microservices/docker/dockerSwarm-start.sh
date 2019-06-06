#!/bin/bash

docker swarm init
SWARM_TOKEN=$(docker swarm join-token -q worker)
SWARM_MASTER_IP=$(docker info | grep -w 'Node Address' | awk '{print $3}')
#DOCKER_VERSION=17.09.1-ce-dind
DOCKER_VERSION=18.09.6-dind
NUM_WORKERS=2

for i in $(seq "${NUM_WORKERS}"); do
	docker run -d --privileged --name worker-${i} --hostname=worker-${i} -p ${i}2375:2375 docker:${DOCKER_VERSION}
	docker --host=localhost:${i}2375 swarm join --token ${SWARM_TOKEN} ${SWARM_MASTER_IP}:2377
done

docker service create \
  --detach=true \
  --name=viz \
  --publish=8000:8080/tcp \
  --constraint=node.role==manager \
  --mount=type=bind,src=/var/run/docker.sock,dst=/var/run/docker.sock \
  dockersamples/visualizer

docker network create -d overlay springcloud-overlay



#docker stack deploy -c compose-simple.yml springcloud-improve
#docker service rm springcloud-improve_zuul-server
#docker service rm springcloud-improve_config-server
