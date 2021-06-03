#!/bin/sh

# CREATE TERRAFORM BACKEND ON HEROKU POSTGRES
# heroku create $TERRAFORM_PG_BACKEND
# heroku addons:create heroku-postgresql:hobby-dev --app $TERRAFORM_PG_BACKEND
## proveri da li postoji vec TERRAFORM_PG_BACKEND

# podize se ova app da bi ostale app kada se pokrecu kontejneri u 
# u runtime mogao da im se prosledi putanja do baze

# ja ovo mogu da iskoristim i za API gateway za putanje a mogu da ih i zakucam
# kada je prod okruzenje

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


# prepare Dockerfile-s

rm -rf ./gateway_service/Dockerfile
rm -rf ./order_service/Dockerfile
rm -rf ./product_service/Dockerfile
rm -rf ./report_service/Dockerfile


echo "FROM $APP_IMAGE_NAME_AGENT_PRODUCT" >> ./product_service/Dockerfile
cat ./product_service/Dockerfile

echo "FROM $APP_IMAGE_NAME_AGENT_OREDER" >> ./order_service/Dockerfile
cat ./order_service/Dockerfile

echo "FROM $APP_IMAGE_NAME_AGENT_REPORT" >> ./report_service/Dockerfile
cat ./report_service/Dockerfile

echo "FROM $APP_IMAGE_NAME_GATEWAY" >> ./gateway_service/Dockerfile
cat ./gateway_service/Dockerfile

# posto se state.json i ostale stvari cuvaju na remote backEnd-u koji se nalazi
# na heroku, ta app na heroku mora da im addOn koji j e postgres baza
# kada se inicializuje teraform prosledjuje se conn_str koji je url do baze u 
# kojoj se biti sacun state builda i ostale stvari koje terraform generise

# conn_str je da bi se tf.state cuvao u toj PG bazi
terraform init -backend-config="conn_str=$DATABASE_URL"

echo "Konektovao sam se na bazu i sad cu da napravim infrastruktru!"

terraform apply -auto-approve -var product_app_name="$APP_NAME_AGENT_PRODUCT" \
                              -var order_app_name="$APP_NAME_AGENT_OREDER"   \
                              -var report_app_name="$APP_NAME_AGENT_REPORT" \
                              -var gateway_app_name="$APP_NAME_AGENT_GATEWAY" \
                              -var gateway_suffix="$API_GATEWAY_SUFFIX"


## provera da li su svi resurs diployovani

