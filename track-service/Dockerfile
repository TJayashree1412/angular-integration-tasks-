FROM openjdk:11.0-jdk
ADD ./target/track-service-0.0.1-SNAPSHOT.jar /muzix/app/track-service-0.0.1-SNAPSHOT.jar
WORKDIR muzix/app
ENTRYPOINT ["java", "-jar", "track-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8088