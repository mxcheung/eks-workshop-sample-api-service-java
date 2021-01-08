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

# Confirm the change

```
If you still have the ELB URL open in your browser, refresh to confirm the update. 
If you need to retrieve the URL again, use kubectl get services hello-k8s -o wide
```

```
cheungm:~/environment $ kubectl get services hello-k8s -o wide
NAME        TYPE           CLUSTER-IP       EXTERNAL-IP                                                                   PORT(S)        AGE    SELECTOR
hello-k8s   LoadBalancer   10.100.195.226   a6499a8ff900e4f03897a8717b9aa145-387724040.ap-southeast-2.elb.amazonaws.com   80:31973/TCP   4m7s   app=hello-k8s
```
Use external ip to access swagger

http://a6499a8ff900e4f03897a8717b9aa145-387724040.ap-southeast-2.elb.amazonaws.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

# Links

https://www.eksworkshop.com/intermediate/220_codepipeline/codepipeline/


http://aed0766c778694ddf9d4b38922522c5a-1492262707.ap-southeast-2.elb.amazonaws.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
