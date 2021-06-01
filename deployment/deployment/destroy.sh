#!/bin/sh

# DESTROY TERRAFORM BACKEND ON HEROKU POSTGRES
# heroku apps:destroy --app $TERRAFORM_PG_BACKEND

ALL_HEROKU_APPS=$(heroku apps) && export ALL_HEROKU_APPS

echo $ALL_HEROKU_APPS
echo $TERRAFORM_PG_BACKEND

if [[ $ALL_HEROKU_APPS =~ "$TERRAFORM_PG_BACKEND" ]]; 
then
  echo "It's there!"
else 
  echo "It's no there!"
  heroku create $TERRAFORM_PG_BACKEND
  heroku addons:create heroku-postgresql:hobby-dev --app $TERRAFORM_PG_BACKEND
fi


cd terraform || exit
DATABASE_URL=$(heroku config:get DATABASE_URL --app "$TERRAFORM_PG_BACKEND") && export DATABASE_URL
terraform init -backend-config="conn_str=$DATABASE_URL"

terraform destroy -auto-approve -var product_app_name="$APP_NAME_AGENT_PRODUCT" \
                                -var order_app_name="$APP_NAME_AGENT_OREDER"   \
                                -var report_app_name="$APP_NAME_AGENT_REPORT" \
                                -var gateway_app_name="$APP_NAME_AGENT_GATEWAY"