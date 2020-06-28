FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
COPY build/libs/shopping-cart-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]