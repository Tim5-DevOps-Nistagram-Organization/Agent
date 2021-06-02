#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar agent-report.jar