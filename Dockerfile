FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY pom.xml ./

COPY src src
RUN mvn package -DskipTests

FROM azul/zulu-openjdk-alpine:17
WORKDIR /app
COPY --from=build /app/target/ams-0.0.1-SNAPSHOT.jar /app/ams.jar
EXPOSE 8080
CMD ["java", "-jar", "ams.jar"]
