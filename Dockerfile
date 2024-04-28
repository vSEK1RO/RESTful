FROM gradle:jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle
WORKDIR /home/gradle/
CMD  ./gradlew bootJar --no-daemon
FROM openjdk:17
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/build/libs/RESTful-0.0.1.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]