FROM openjdk:17
EXPOSE 8080:8080
RUN mkdir /home/gradle
COPY . home/gradle
WORKDIR /home/gradle/
CMD ./gradlew bootJar --no-daemon
ENTRYPOINT ["java","-jar","/home/gradle/build/libs/RESTful-0.0.1.jar"]