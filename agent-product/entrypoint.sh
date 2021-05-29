#!/bin/sh

./consul agent -config-dir=/consul-config &

java -jar agent-product.jar