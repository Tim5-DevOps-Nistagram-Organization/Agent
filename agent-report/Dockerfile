FROM maven:3.8.1-jdk-11 AS agentReportServiceTest
ARG STAGE=test
WORKDIR /usr/src/server
COPY . .

FROM maven:3.8.1-jdk-11 AS agentReportServiceBuild
ARG STAGE=dev
WORKDIR /usr/src/server
COPY . .
RUN mvn package -P${STAGE} -DskipTests

FROM openjdk:11.0-jdk AS agentReportServiceRuntime
WORKDIR /app
COPY --from=agentOrderServiceBuild /usr/src/server/target/servers.jar ./
EXPOSE 8080
CMD java -jar servers.jar