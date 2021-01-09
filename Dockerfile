FROM maven:3.6.1-amazoncorretto-8 as BUILD

COPY ./service /usr/src/app
RUN mvn -Dmaven.repo.local=/root/m2 --batch-mode -f /usr/src/app/pom.xml clean package

FROM openjdk:8-jre-slim
VOLUME /tmp
EXPOSE 8080
COPY --from=BUILD /usr/src/app/target/mythical-mysfits-1.0-SNAPSHOT.jar /opt/target/app.jar
WORKDIR /opt/target

CMD ["java", "-jar", "app.jar"]
