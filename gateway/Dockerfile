FROM maven:3.6.3-ibmjava-8-alpine  AS gatewayBuild
ARG STAGE=dev
WORKDIR /usr/src/server
COPY . .
RUN mvn package -P${STAGE} -DskipTests


FROM openjdk:8-jdk-alpine AS gatewayRuntime
WORKDIR /app
COPY entrypoint.sh /entrypoint.sh
COPY consul-client.json /consul-config/consul-client.json
RUN apk --no-cache add \
    curl \
    unzip \
    && curl https://releases.hashicorp.com/consul/1.7.2/consul_1.7.2_linux_amd64.zip -o consul.zip \
    && unzip consul.zip \
    && chmod +x consul \
    && rm -f consul.zip \
    && chmod +x /entrypoint.sh \
    && apk --no-cache del \
    && mkdir consul-data \
    curl \
    unzip

COPY --from=gatewayBuild /usr/src/server/target/gateway-1.0.0.jar gateway-1.0.0.jar
CMD ["/entrypoint.sh"]
