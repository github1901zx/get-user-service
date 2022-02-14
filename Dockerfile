FROM openjdk:11
WORKDIR /code
COPY . /code
EXPOSE 9999
ADD pom.xml /code/pom/xml
ADD src /code/src
ARG JAR_FILE=target/*.jar
ADD target/*.jar get-user-service
COPY ${JAR_FILE} /code/get-user-service.jar
ENTRYPOINT ["java","-jar","get-user-service"]