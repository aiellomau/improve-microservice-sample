# Docker Swarn mode
### Init Docker Swarm master
```sh
docker swarm init
```
### Get join token. This token is shared between all workers cluster:
```sh
SWARM_TOKEN=$(docker swarm join-token -q worker)
echo $SWARM_TOKEN
```
### Get Swarm master IP (Docker VM IP)
```sh
SWARM_MASTER_IP=$(docker info | grep -w 'Node Address' | awk '{print $3}')
echo $SWARM_MASTER_IP
```
### Fetch Docker version for future donwload on each worker
```sh
DOCKER_VERSION=17.09.1-ce-dind
```
### Number of workers (3 for now)
```sh
NUM_WORKERS=3
```
### Run NUM_WORKERS workers with SWARM_TOKEN. This could be on a bash script:
```sh
for i in $(seq "${NUM_WORKERS}"); do
	docker run -d --privileged --name worker-${i} --hostname=worker-${i} -p ${i}2375:2375 docker:${DOCKER_VERSION}
	docker --host=localhost:${i}2375 swarm join --token ${SWARM_TOKEN} ${SWARM_MASTER_IP}:2377
done
```
### Setup the visualizer (will run at http:127.0.0.1:8080)
```sh
docker service create \
  --detach=true \
  --name=viz \
  --publish=8000:8080/tcp \
  --constraint=node.role==manager \
  --mount=type=bind,src=/var/run/docker.sock,dst=/var/run/docker.sock \
  dockersamples/visualizer
```

### Use our Spring Microservice with docker
On the root folder ../ crea a Dockerfile:
>FROM alpine:edge
>MAINTAINER javaonfly
>RUN apk add --no-cache openjdk8
### Build base image
```sh
docker build --tag=alpine-jdk:base --rm=true .
```
### Example doing the eureka server microservice with Docker.
Go to eureka-server project. cd $eureka_project
```sh
nano Dockerfile-EurekaServer
```
> FROM alpine-jdk:base
> MAINTAINER javaonfly
> COPY target/eureka-server-0.0.1-SNAPSHOT.jar /opt/lib/
> ENTRYPOINT ["/usr/bin/java"]
> CMD ["-jar", "/opt/lib/eureka-server-0.0.1-SNAPSHOT.jar"]
> EXPOSE 8761

Build eureka-server image.
Before build microservice
```sh
mvn clean install
```
Build ms image
```sh
docker build --file=Dockerfile-EurekaServer --tag=eureka-server:latest --rm=true .
```
### (Optionally) Test docker image run
```sh
docker run --name=eureka-server --publish=8761:8761 eureka-server:latest
```

### Create swarn service for eureka-server
```sh
docker service create --name eureka-server --detach=false -p 8761:8761 eureka-server:latest
```

### Finally Scale
Scale up to 4 instances
```sh
docker service scale eureka-service=4 --detach=true
```
### Remove service
```sh
docker service rm eureka-server
```

### Unregister worker nodes
for i in $(seq "${NUM_WORKERS}"); do
	docker --host=localhost:${i}2375 swarm leave
done

### Remove worker nodes
```sh
docker rm -f $(docker ps -a -q --filter ancestor=docker:${DOCKER_VERSION} --format="")		
```

### Leave Swarm mode
```sh
docker swarm leave --force
```
