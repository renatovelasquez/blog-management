FROM amazoncorretto:21-alpine-jdk
RUN adduser -D demo
USER demo
VOLUME /tmp
ADD build/libs/blog-management-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]