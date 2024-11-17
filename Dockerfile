FROM registry.access.redhat.com/ubi8/openjdk-17:1.20-2.1730773722
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app/gendockerfile-api.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gendockerfile-api.jar"]