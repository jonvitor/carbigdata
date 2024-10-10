FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/carbigdata-api-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh

RUN chmod +x /wait-for-it.sh

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]