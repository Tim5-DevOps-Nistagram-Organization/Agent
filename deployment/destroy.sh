#!/bin/bash

HEROKU_EMAIL=${1}
HEROKU_API_KEY=${2}
VERSION=${3}
TERRAFORM_PG_BACKEND=${4}
NAME=${5:-staging}
DOCKERHUB_USERNAME=${6:-dusanbucan}
CONTAINER_NAME=${7:-terraform-deploy}

APP_NAME_AGENT_PRODUCT=agentproduct
APP_NAME_AGENT_OREDER=agentorder
APP_NAME_AGENT_REPORT=agentreport
APP_NAME_AGENT_GATEWAY=agentgateway

APP_IMAGE_NAME_AGENT_PRODUCT=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_PRODUCT}:${VERSION}
APP_IMAGE_NAME_AGENT_OREDER=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_OREDER}:${VERSION}
APP_IMAGE_NAME_AGENT_REPORT=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_REPORT}:${VERSION}
APP_IMAGE_NAME_GATEWAY=${DOCKERHUB_USERNAME}/${APP_NAME_AGENT_GATEWAY}:${VERSION}


APP_NAME_AGENT_PRODUCT_HEROKU=agentproduct${NAME}
APP_NAME_AGENT_OREDER_HEROKU=agentorder${NAME}
APP_NAME_AGENT_REPORT_HEROKU=agentreport${NAME}
APP_NAME_AGENT_GATEWAY_HEROKU=agentgateway${NAME}

# create can be used to create container from base image
# add new files, env variables
# and then in separete comand to start container

# here we use it for running terraform scripts to deploy on Heroku staging and prod env
# we override CMD but ENTRYPOINT probably does heroku login
DOCKER_BUILDKIT=1 docker create \
  --workdir /deployment \
  --entrypoint sh \
  --env APP_IMAGE_NAME_AGENT_PRODUCT="${APP_IMAGE_NAME_AGENT_PRODUCT}" \
  --env APP_IMAGE_NAME_AGENT_OREDER="${APP_IMAGE_NAME_AGENT_OREDER}" \
  --env APP_IMAGE_NAME_AGENT_REPORT="${APP_IMAGE_NAME_AGENT_REPORT}" \
  --env APP_IMAGE_NAME_GATEWAY="${APP_IMAGE_NAME_GATEWAY}" \
  --env APP_NAME_AGENT_PRODUCT="${APP_NAME_AGENT_PRODUCT_HEROKU}" \
  --env APP_NAME_AGENT_OREDER="${APP_NAME_AGENT_OREDER_HEROKU}" \
  --env APP_NAME_AGENT_REPORT="${APP_NAME_AGENT_REPORT_HEROKU}" \
  --env APP_NAME_AGENT_GATEWAY="${APP_NAME_AGENT_GATEWAY_HEROKU}" \
  --env HEROKU_API_KEY="${HEROKU_API_KEY}" \
  --env HEROKU_EMAIL="${HEROKU_EMAIL}" \
  --env TERRAFORM_PG_BACKEND="${TERRAFORM_PG_BACKEND}" \
  --name "$CONTAINER_NAME" \
  danijelradakovic/heroku-terraform \
  destroy.sh

docker cp deployment/. "${CONTAINER_NAME}":/deployment/
docker start -i "${CONTAINER_NAME}"
docker rm "${CONTAINER_NAME}"
