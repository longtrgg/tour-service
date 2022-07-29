FROM openjdk:8-jdk-alpine
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
RUN ./mvnw clean install -D skipTests
CMD ["./mvnw", "spring-boot:run"]