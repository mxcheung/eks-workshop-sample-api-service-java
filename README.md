# eks-workshop-sample-api-service-java
eks-workshop-sample-api-service-java


# Step by Step

1.  Build Docker image and test

```
cd ..
docker build . -t 918300033687.dkr.ecr.ap-southeast-2.amazonaws.com/mythicalmysfits/service:latest

docker run -p 8081:8080 918300033687.dkr.ecr.ap-southeast-2.amazonaws.com/mythicalmysfits/service:latest

docker push 918300033687.dkr.ecr.ap-southeast-2.amazonaws.com/mythicalmysfits/service:latest

aws ecr create-repository --repository-name mythicalmysfits/service
```
2. Update hello-k8s.yml

Replace CONTAINER_IMAGE

```
          image: CONTAINER_IMAGE
          image: 918300033687.dkr.ecr.ap-southeast-2.amazonaws.com/mythicalmysfits/service:latest
```

kubectl apply -f hello-k8s.yml

3. Install code pipeline via cloud formation
Stuck here are GO Dockerfile multi-stage

```
The Dockerfile is a multi-stage build that compiles the Go application and then packages it in a minimal image that pulls from scratch. The size of this Docker image is ~ 3.2 MiB.
```


# Links

https://www.eksworkshop.com/intermediate/220_codepipeline/codepipeline/


http://aed0766c778694ddf9d4b38922522c5a-1492262707.ap-southeast-2.elb.amazonaws.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
