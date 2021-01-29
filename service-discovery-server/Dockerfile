FROM openjdk:11-jre-slim
LABEL Description="Eureka Server"
COPY /target/service-discovery-server.jar /app/app.jar
EXPOSE 8761
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/app.jar" ]