# eks-workshop-sample-api-service-java
eks-workshop-sample-api-service-java

![Architecture Diagram](https://github.com/mxcheung/eks-workshop-sample-api-service-java/blob/main/eks.png)

# Step by Step

1.  Build Docker image and test

```
cd ..
docker build . -t <ACCOUNT_ID>.dkr.ecr.ap-southeast-2.amazonaws.com/mythicalmysfits/service:latest

docker run -p 8081:8080 <ACCOUNT_ID>.dkr.ecr.ap-southeast-2.amazonaws.com/mythicalmysfits/service:latest

docker push <ACCOUNT_ID>.dkr.ecr.ap-southeast-2.amazonaws.com/mythicalmysfits/service:latest

aws ecr create-repository --repository-name mythicalmysfits/service
```
2. Update hello-k8s.yml

Replace CONTAINER_IMAGE

```
          image: CONTAINER_IMAGE
          image: <ACCOUNT_ID>.dkr.ecr.ap-southeast-2.amazonaws.com/mythicalmysfits/service:latest
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


# Dynamodb

```
{
  "timestamp": "2021-01-10T09:43:50.821+0000",
  "status": 500,
  "error": "Internal Server Error",
  "message": "User: arn:aws:sts::<ACCOUNT_ID>:assumed-role/eksctl-eksworkshop-eksctl-nodegro-NodeInstanceRole-1CKGIFBC410Q0/i-0fdc024a3b4c1e8b7 is not authorized to perform: dynamodb:Scan on resource: arn:aws:dynamodb:ap-southeast-2:<ACCOUNT_ID>:table/MysfitsTable (Service: AmazonDynamoDBv2; Status Code: 400; Error Code: AccessDeniedException; Request ID: A5BSOI9FLV6537SVT29HC3PBC3VV4KQNSO5AEMVJF66Q9ASUAAJG)",
  "path": "/mysfits"
}
```

### Attaching policies by ARN

```
managedNodeGroups:
- name: nodegroup
  iam:
    attachPolicyARNs:
      - arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy
      - arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly
      - arn:aws:iam::aws:policy/AmazonEKS_CNI_Policy
      - arn:aws:iam::aws:policy/ElasticLoadBalancingFullAccess
      - arn:aws:iam::<ACCOUNT_ID>:policy/aws-ddb-policy1
      
```
        
# Links

https://www.eksworkshop.com/intermediate/220_codepipeline/codepipeline/


http://aed0766c778694ddf9d4b38922522c5a-1492262707.ap-southeast-2.elb.amazonaws.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

https://dzone.com/articles/multi-stage-docker-image-build-for-java-apps

https://mailslurp.medium.com/faster-java-containers-with-docker-multi-stage-builds-cc63e056e546

https://mythicalmysfits.com/

https://cmani.medium.com/pod-level-access-to-dynamodb-using-iam-on-amazon-eks-eeabd5460cb6

http://cheungm-eksworkshop-2021-01-10.s3-ap-southeast-2.amazonaws.com/index.html

https://eksctl.io/usage/iam-policies/

http://abb0a79a69752428aad6bc6d8364cda2-1022846971.ap-southeast-2.elb.amazonaws.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
