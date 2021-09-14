FROM openjdk:11-jre-slim

WORKDIR /app

EXPOSE 8082

ENV DATABASE_CONNECTION_URL="jdbc:mysql://db:3306/debitodb"

COPY target/debito.jar /app/debito.jar

ENTRYPOINT ["java", "-jar", "debito.jar"]
