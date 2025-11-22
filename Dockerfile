FROM amazoncorretto:21-alpine3.15
LABEL MANTAINER="PERSON"

WORKDIR /app

EXPOSE 8082

RUN wget -O dd-java-agent.jar 'https://dtdg.co/latest-java-tracer'

COPY springframework/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
