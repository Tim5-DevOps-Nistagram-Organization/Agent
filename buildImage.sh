#!/bin/bash

STAGE=${1}
VERSION=${2}
DOCKERHUB_PASSWORD=${3}
DOCKERHUB_USERNAME=${4:-dusanbucan}

APP_NAME_AGENT_PRODUCT=agentproduct
APP_NAME_AGENT_OREDER=agentorder
APP_NAME_AGENT_REPORT=agentreport
APP_IMAGE_NAME_AGENT_PRODUCT=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_PRODUCT}:${VERSION}
APP_IMAGE_NAME_AGENT_OREDER=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_OREDER}:${VERSION}
APP_IMAGE_NAME_AGENT_REPORT=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_REPORT}:${VERSION}

# ako je prod da ima 2 taga pored verzionisanog da ima i latest tag
# TODO: ovo isto zavisi od ENV varijable a ja sam zakucao ovako jer ne mogu da push na njegov docker hub
docker build \
-t "${APP_IMAGE_NAME_AGENT_PRODUCT}" \
--target agentProductServiceRuntime \
--build-arg STAGE=${STAGE} \
--build-arg DOMAIN=" domain: '${APP_NAME_AGENT_PRODUCT}.herokuapp.com'," \
--build-arg PORT="  port: ''," \
--no-cache \
./agent-product

docker build \
-t "${APP_IMAGE_NAME_AGENT_OREDER}" \
--target agentOrderServiceRuntime \
--build-arg STAGE=${STAGE} \
--build-arg DOMAIN=" domain: '${APP_NAME_AGENT_OREDER}.herokuapp.com'," \
--build-arg PORT="  port: ''," \
--no-cache \
./agent-order

docker build \
-t "${APP_IMAGE_NAME_AGENT_REPORT}" \
--target agentReportServiceRuntime \
--build-arg STAGE=${STAGE} \
--build-arg DOMAIN=" domain: '${APP_NAME_AGENT_REPORT}.herokuapp.com'," \
--build-arg PORT="  port: ''," \
--no-cache \
./agent-report

docker login --username ${DOCKERHUB_USERNAME} --password=${DOCKERHUB_PASSWORD}
docker push "$APP_IMAGE_NAME_AGENT_PRODUCT"
docker push "$APP_IMAGE_NAME_AGENT_OREDER"
docker push "$APP_IMAGE_NAME_AGENT_REPORT"

