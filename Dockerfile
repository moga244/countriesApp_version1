FROM openjdk:8

LABEL maintainer="hassan.magdy@hotmail.com"

COPY target/countriesapp-0.0.1-SNAPSHOT.jar /countriesapp.jar
EXPOSE 8080

CMD ["java", "-jar", "countriesapp.jar"]