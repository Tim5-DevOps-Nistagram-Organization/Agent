#!/bin/bash

tokenProduct=${1}
tokenOrder=${2}
tokenReport=${3}
branch=${4}
pullKey=${5:-0}
base=${6:-main}

source ./config/.env.test

if [ "$pullKey" = "0" ]; then
  SONAR_TOKEN_PRODUCT=${tokenProduct} SONAR_TOKEN_ORDER=${tokenOrder} SONAR_TOKEN_REPORT=${tokenReport} BRANCH_NAME=${branch} docker-compose -f ./docker-compose.test.yml  --env-file ./config/.env.test up -d
else
  SONAR_TOKEN_PRODUCT=${tokenProduct} SONAR_TOKEN_ORDER=${tokenOrder} SONAR_TOKEN_REPORT=${tokenReport} PULL_KEY=${pullKey} PULL_REQUEST_BRANCH=${branch} BASE=${base} docker-compose -f ./docker-compose.test.yml  --env-file ./config/.env.test up -d
fi

is_finished() {
    service="$1"
    container_id="$(docker-compose -f ./docker-compose.test.yml ps -q "$service")"
    health_status="$(docker inspect -f "{{.State.Status}}" "$container_id")"

    echo "STATUS: $health_status, CONTAINER: $service"

    if [ "$health_status" = "exited" ]; then
        return 0
    else
        return 1
    fi
}

## ================== Test for service: agentOrderService =================

while ! is_finished agent-order-service; do sleep 20; done
# provera Quality Gate-a i da li je neki od testova pao
servers_logs=$(docker logs agent-order --tail 20)
python ./sonar-maven-breaker.py --testLogs "${servers_logs}" --projectKey ${SONAR_PROJ_KEY_ORDER_SVC}  --pullRequestNumber ${pullKey} --branch ${branch}



## ================== Test for service: agentProduceService =================

while ! is_finished agent-product-service; do sleep 20; done
# provera Quality Gate-a i da li je neki od testova pao
servers_logs=$(docker logs agent-product --tail 20)
python ./sonar-maven-breaker.py --testLogs "${servers_logs}" --projectKey ${SONAR_PROJ_KEY_PRODUCT_SVC}  --pullRequestNumber ${pullKey} --branch ${branch}



## ================== Test for service: agentReportService =================

while ! is_finished agent-report-service; do sleep 20; done
# provera Quality Gate-a i da li je neki od testova pao
servers_logs=$(docker logs agent-report --tail 20)
python ./sonar-maven-breaker.py --testLogs "${servers_logs}" --projectKey ${SONAR_PROJ_KEY_REPORT_SVC}  --pullRequestNumber ${pullKey} --branch ${branch}


