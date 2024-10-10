# Base image được sử dụng để build image
FROM --platform=amd64 openjdk:21-jdk

LABEL authors="khietbt"

WORKDIR /app

COPY target/sesame-config-service-1.0.0.jar sesame-config-service-1.0.0.jar

EXPOSE 8001

CMD ["java", "-jar", "sesame-config-service-1.0.0.jar"]