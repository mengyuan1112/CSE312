FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

WORKDIR /build/


COPY pom.xml/ /build
COPY src/ /build/src


RUN mvn clean package -DskipTests

FROM openjdk:8-jre-alpine

WORKDIR /app/

ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.2.1/wait /wait
RUN chmod +x /wait

COPY --from=MAVEN_BUILD /build/target/demo-0.0.1-SNAPSHOT.jar /app/



#RUN mvn clean compile assembly:single
#ARG JAR_FILE=target/*.jar
#
#RUN cp ${JAR_FILE} app.jar


CMD /wait && java -jar demo-0.0.1-SNAPSHOT.jar
#CMD java -jar demo-0.0.1-SNAPSHOT.jar
#ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]
