#!/bin/bash

secretToken=${1}  # passed via pipeline

source ./config/.env.test


SONAR_TOKEN=${secretToken} docker-compose -f ./docker-compose.test.yml  --env-file ./config/.env.test up -d

is_finished() {
    service="$1"
    container_id="$(docker-compose ps -q "$service")"
    health_status="$(docker inspect -f "{{.State.Status}}" "$container_id")"

    echo "STATUS: $health_status"

    if [ "$health_status" = "exited" ]; then
        return 0
    else
        return 1
    fi
}

## ================== Test for service: agentOrderService =================

while ! is_finished agentOrderService; do sleep 20; done
# provera Quality Gate-a i da li je neki od testova pao
servers_logs=$(docker logs agentOrderService --tail 20)
python ./sonar-maven-breaker.py --testLogs "${servers_logs}" --projectKey ${SONAR_PROJ_KEY_ORDER_SVC} --branch "develop"



## ================== Test for service: agentProduceService =================

while ! is_finished agentProduceService; do sleep 20; done
# provera Quality Gate-a i da li je neki od testova pao
servers_logs=$(docker logs agentProduceService --tail 20)
python ./sonar-maven-breaker.py --testLogs "${servers_logs}" --projectKey ${SONAR_PROJ_KEY_PRODUCE_SVC} --branch "develop"



## ================== Test for service: agentReportService =================

while ! is_finished agentReportService; do sleep 20; done
# provera Quality Gate-a i da li je neki od testova pao
servers_logs=$(docker logs agentReportService --tail 20)
python ./sonar-maven-breaker.py --testLogs "${servers_logs}" --projectKey ${SONAR_PROJ_KEY_REPORT_SVC} --branch "develop"


