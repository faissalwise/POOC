FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    PROACTIVE_SLEEP=0 \
    JAVA_OPTS=""

# add directly the jar
ADD *.jar /app.jar

EXPOSE 8082

CMD echo "The application will started..." && \
    java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar