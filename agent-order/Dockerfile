FROM maven:3.8.1-jdk-11 AS agentOrderServiceTest
ARG STAGE=test
WORKDIR /usr/src/server
COPY . .

FROM maven:3.8.1-jdk-11  AS agentOrderServiceBuild
ARG STAGE=dev
WORKDIR /usr/src/server
COPY . .
RUN mvn package -P${STAGE} -DskipTests


FROM openjdk:11.0-jdk as agentOrderServiceRuntime
COPY entrypoint.sh /entrypoint.sh
COPY consul-client.json /consul-config/consul-client.json
RUN apt-get install -y \
    curl \
    unzip  \
    && curl https://releases.hashicorp.com/consul/1.9.5/consul_1.9.5_linux_amd64.zip -o consul.zip \
    && unzip consul.zip \
    && chmod +x consul \
    && rm -f consul.zip \
    && chmod +x /entrypoint.sh \
    && mkdir consul-data \
    && apt-get remove \
    curl \
    unzip -y

COPY --from=agentOrderServiceBuild /usr/src/server/target/*.jar agent-order.jar
EXPOSE 8080
CMD ["/entrypoint.sh"]
