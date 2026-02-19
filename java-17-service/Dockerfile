FROM eclipse-temurin:17-jre-jammy

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

WORKDIR /app

COPY target/*.jar app.jar

ENV JAVA_OPTS="-XX:InitialRAMPercentage=75.0 -XX:MaxRAMPercentage=75.0 -XX:+FlightRecorder"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]