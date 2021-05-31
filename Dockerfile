## =================> STAGES for OrderService <=======================
FROM maven:3.8.1-jdk-11 AS agentOrderServiceTest
ARG STAGE=test
WORKDIR /usr/src/server
COPY ./agent-order .


FROM maven:3.8.1-jdk-11  AS agentOrderServiceBuild
ARG STAGE=dev
WORKDIR /usr/src/server
COPY ./agent-order .
RUN mvn package -P${STAGE} -DskipTests


FROM openjdk:11.0-jdk as agentOrderServiceRuntimeDev
COPY ./agent-order/entrypoint.sh /entrypoint.sh
COPY ./agent-order/consul-client.json /consul-config/consul-client.json
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



FROM openjdk:11.0-jdk as agentOrderServiceRuntimeProd
COPY --from=agentOrderServiceBuild /usr/src/server/target/*.jar agent-order.jar
CMD java -jar agent-order.jar



## =================> STAGES for ProductService <=======================
FROM maven:3.8.1-jdk-11 AS agentProductServiceTest
ARG STAGE=test
WORKDIR /usr/src/server
COPY ./agent-product .

FROM maven:3.8.1-jdk-11  AS agentProductServiceBuild
ARG STAGE=dev
WORKDIR /usr/src/server
COPY ./agent-product .
RUN mvn package -P${STAGE} -DskipTests

FROM openjdk:11.0-jdk as agentProductServiceRuntimeDev
COPY ./agent-product/entrypoint.sh /entrypoint.sh
COPY ./agent-product/consul-client.json /consul-config/consul-client.json
RUN apt-get install -y \
    curl \
    unzip \
    && curl https://releases.hashicorp.com/consul/1.9.5/consul_1.9.5_linux_amd64.zip -o consul.zip \
    && unzip consul.zip \
    && chmod +x consul \
    && rm -f consul.zip \
    && chmod +x /entrypoint.sh \
    && mkdir consul-data \
    && apt-get remove -y \
    curl \
    unzip

COPY --from=agentProductServiceBuild /usr/src/server/target/*.jar agent-product.jar
EXPOSE 8080
CMD ["/entrypoint.sh"]



FROM openjdk:11.0-jdk as agentProductServiceRuntimeProd
COPY --from=agentProductServiceBuild /usr/src/server/target/*.jar agent-product.jar
CMD java -jar agent-product.jar


## =================> STAGES for ReportService <=======================

FROM maven:3.8.1-jdk-11 AS agentReportServiceTest
ARG STAGE=test
WORKDIR /usr/src/server
COPY ./agent-report .

FROM maven:3.8.1-jdk-11  AS agentReportServiceBuild
ARG STAGE=dev
WORKDIR /usr/src/server
COPY ./agent-report .
RUN mvn package -P${STAGE} -DskipTests

FROM openjdk:11.0-jdk as agentReportServiceRuntimeDev
COPY ./agent-report/entrypoint.sh /entrypoint.sh
COPY ./agent-report/consul-client.json /consul-config/consul-client.json
RUN apt-get install -y \
    curl \
    unzip \
    && curl https://releases.hashicorp.com/consul/1.9.5/consul_1.9.5_linux_amd64.zip -o consul.zip \
    && unzip consul.zip \
    && chmod +x consul \
    && rm -f consul.zip \
    && chmod +x /entrypoint.sh \
    && mkdir consul-data \
    && apt-get remove -y \
    curl \
    unzip

COPY --from=agentReportServiceBuild /usr/src/server/target/*.jar agent-report.jar
EXPOSE 8080
CMD ["/entrypoint.sh"]



FROM openjdk:11.0-jdk as agentReportServiceRuntimeProd
COPY --from=agentReportServiceBuild /usr/src/server/target/*.jar agent-report.jar
CMD java -jar agent-report.jar


## =================> STAGES for FrontEnd <=====================
FROM node:13.12.0-alpine as frontEndBuild

ARG PROTOCOL="  protocol: 'https',"
ARG DOMAIN="  domain: 'localhost',"
ARG PORT="  port: '8080',"
ARG API="  api: '/api/server'"

# set working directory
WORKDIR /usr/src

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install app dependencies
COPY ./agent-web/package.json ./
COPY ./agent-web/package-lock.json ./
RUN npm install

# add app
COPY ./agent-web/ ./
RUN npm run build --prod

## run React tests
FROM node:13.12.0-alpine as frontEndTest
ARG PROTOCOL="  protocol: 'http',"
ARG DOMAIN="  domain: 'localhost',"
ARG PORT="  port: '8080',"
ARG API="  api: '/api/server'"
WORKDIR /usr/src
ENV PATH /app/node_modules/.bin:$PATH
COPY ./agent-web/package.json ./
COPY ./agent-web/package-lock.json ./
RUN npm install
COPY ./agent-web/ ./



## =================> STAGES for Gateway <=======================
FROM maven:3.6.3-ibmjava-8-alpine  AS gatewayBuild
ARG STAGE=dev
WORKDIR /usr/src/server
COPY ./gateway .
COPY --from=frontEndBuild /usr/src/build/index.html ./src/main/resources/
COPY --from=frontEndBuild /usr/src/build/asset-manifest.json ./src/main/resources/
COPY --from=frontEndBuild /usr/src/build/ ./src/main/resources/static
RUN mvn package -P${STAGE} -DskipTests 


FROM openjdk:8-jdk-alpine AS gatewayRuntimeDev
WORKDIR /app
COPY ./gateway/entrypoint.sh /entrypoint.sh
COPY ./gateway/consul-client.json /consul-config/consul-client.json
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



FROM openjdk:8-jdk-alpine AS gatewayRuntimeProd
WORKDIR /app
COPY --from=gatewayBuild /usr/src/server/target/gateway-1.0.0.jar gateway
CMD java -jar gateway




## =================> STAGES for Consul server <=======================
FROM consul:1.9.5 as consulServerDev
COPY ./consul-server/entrypoint.sh /entrypoint.sh
COPY ./consul-server /consul/config/
RUN chmod +x /entrypoint.sh
EXPOSE 8500
CMD ["/entrypoint.sh"]
