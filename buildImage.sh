#!/bin/bash

STAGE=${1}
VERSION=${2}
DOCKERHUB_PASSWORD=${3}
DOCKERHUB_USERNAME=${4:-dusanbucan}

APP_NAME_AGENT_PRODUCT=agentproduct
APP_NAME_AGENT_OREDER=agentorder
APP_NAME_AGENT_REPORT=agentreport
APP_NAME_AGENT_GATEWAY=gateway

APP_IMAGE_NAME_AGENT_PRODUCT=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_PRODUCT}:${VERSION}
APP_IMAGE_NAME_AGENT_OREDER=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_OREDER}:${VERSION}
APP_IMAGE_NAME_AGENT_REPORT=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_REPORT}:${VERSION}
APP_IMAGE_NAME_GATEWAY=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_GATEWAY}:${VERSION}

DOCKER_BUILDKIT=1 docker build \
-t "${APP_IMAGE_NAME_AGENT_PRODUCT}" \
--target agentProductServiceRuntimeProd \
--build-arg STAGE=${STAGE} \
--no-cache \
.

DOCKER_BUILDKIT=1 docker build \
-t "${APP_IMAGE_NAME_AGENT_OREDER}" \
--target agentOrderServiceRuntimeProd \
--build-arg STAGE=${STAGE} \
--no-cache \
.

DOCKER_BUILDKIT=1 docker build \
-t "${APP_IMAGE_NAME_AGENT_REPORT}" \
--target agentReportServiceRuntimeProd \
--build-arg STAGE=${STAGE} \
--no-cache \
.

DOCKER_BUILDKIT=1 docker build \
-t "${APP_IMAGE_NAME_GATEWAY}" \
--target gatewayRuntimeProd \
--build-arg STAGE=${STAGE} \
--build-arg DOMAIN=" domain: '${APP_IMAGE_NAME_GATEWAY}.herokuapp.com'," \
--build-arg PORT="  port: ''," \
--no-cache \
.

docker login --username ${DOCKERHUB_USERNAME} --password=${DOCKERHUB_PASSWORD}
docker push "$APP_IMAGE_NAME_AGENT_PRODUCT"
docker push "$APP_IMAGE_NAME_AGENT_OREDER"
docker push "$APP_IMAGE_NAME_AGENT_REPORT"
docker push "$APP_IMAGE_NAME_GATEWAY"
