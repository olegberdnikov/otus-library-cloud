FROM openjdk:11-jre-slim
EXPOSE 8080
COPY src/main/resources/docker/wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh
COPY /target/facade-gateway.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar" ]