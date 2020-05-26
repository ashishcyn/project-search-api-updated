FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} project-search-api.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/project-search-api.jar"]
