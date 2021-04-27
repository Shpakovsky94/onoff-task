FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/onoff-spring-boot.jar onoff-spring-boot.jar
EXPOSE 8070
ENTRYPOINT ["java", "-jar", "onoff-spring-boot.jar"]
