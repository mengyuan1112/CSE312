FROM openjdk:8-jdk-alpine

CMD ["mvn", "clean", "-DskipTests"]

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]