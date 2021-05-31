#!/bin/sh

# DESTROY TERRAFORM BACKEND ON HEROKU POSTGRES
# heroku apps:destroy --app $TERRAFORM_PG_BACKEND

cd terraform || exit
DATABASE_URL=$(heroku config:get DATABASE_URL --app "$TERRAFORM_PG_BACKEND") && export DATABASE_URL
terraform init -backend-config="conn_str=$DATABASE_URL"

terraform destroy -auto-approve -var product_app_name="agentproduct" \
                              -var order_app_name="agentorder"   \
                              -var report_app_name="agentreport" \
                              -var gateway_app_name="agentgateway"