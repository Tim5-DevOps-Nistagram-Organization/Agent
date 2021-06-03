terraform {
  backend "pg" {
  }
}


# ovde se koristi njegov custom made provider, tj. iz tog image koji je on push-no na DockerHub
provider "heroku" {
}

# nema setovanu ni default value pa ce uzeti SYS ENV koji je podesen
# kada se pokrece:
# ./deploy.sh dusanProba dusanbzr@gmail.com 8481fb14-b166-4277-984d-8defb98f5841 pgb1
variable "product_app_name" {
  description = "Unique name of the product service app"
}
variable "order_app_name" {
  description = "Unique name of the order service app"
}
variable "report_app_name" {
  description = "Unique name of the report service app"
}
variable "gateway_app_name" {
  description = "Unique name of the gateway service app"
}

variable "gateway_suffix" {
  description = "suffix to add in URLs for app route "
  default = "staging"
}

# ovo koristi kako bi podesio sistemsku varijablu u dyno
# tu sistemsku varijblu je koristio da aktivira konfiguracionu klasu u springu
# da bi ucitao jdbc za za add-on za bazu 
resource "heroku_config" "prod" {
  vars = {

    STAGE = "PROD"
    GATEWAY_SUFFIX = var.gateway_suffix
  }

}

resource "heroku_app" "product_service" {
  name   = var.product_app_name
  region = "eu"
  # mora stack = container da bi u bildu koristio Dockerfile a ne Procfile
  # treba ti heroku.yml da bi se odredilo za koji build heroku app treba koji Dockerfile
  stack  = "container"
}

resource "heroku_build" "product_service" {
  app = heroku_app.product_service.id

  source {
    path = "product_service"
  }
}

# povezuje app i konfiguraciju
# ovo je kao da si na tu masinu stavio SYS ENV
resource "heroku_app_config_association" "product_service" {
  app_id = heroku_app.product_service.id
  vars = heroku_config.prod.vars
}

# dodaje bazu
# fora kako se izvuce url baze i da konteneru pri pokretanju?
#   Spring app verovatno izvuce kao ENV nekako
# koristi istu bazu za:
#   server1
#   server2 samo je heroku_addon_attachment na ovu bazu

resource "heroku_addon" "database" {
  app  = heroku_app.product_service.name
  plan = "heroku-postgresql:hobby-dev"
}


## =========================  order service ============================ ##
resource "heroku_app" "order_service" {
  name   = var.order_app_name
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "order_service" {
  app = heroku_app.order_service.id

  source {
    path = "order_service"
  }
}

resource "heroku_app_config_association" "order_service" {
  app_id = heroku_app.order_service.id

  vars = heroku_config.prod.vars
}

resource "heroku_addon_attachment" "database" {
  app_id  = heroku_app.order_service.id
  addon_id = heroku_addon.database.id
}


## =========================  report service ============================ ##
resource "heroku_app" "report_service" {
  name   = var.report_app_name
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "report_service" {
  app = heroku_app.report_service.id

  source {
    path = "report_service"
  }
}

resource "heroku_app_config_association" "report_service" {
  app_id = heroku_app.report_service.id

  vars = heroku_config.prod.vars
}

resource "heroku_addon_attachment" "database1" {
  app_id  = heroku_app.report_service.id
  addon_id = heroku_addon.database.id
}

## =========================  gateway service ============================ ##
resource "heroku_app" "gateway_service" {
  name   = var.gateway_app_name
  region = "eu"
  stack  = "container"
}

resource "heroku_build" "gateway_service" {
  app = heroku_app.gateway_service.id

  source {
    path = "gateway_service"
  }
}

resource "heroku_app_config_association" "gateway_service" {
  app_id = heroku_app.gateway_service.id
  vars = heroku_config.prod.vars
}




## =========================  Outputs ============================ ##

output "product_service_url" {
  value = "https://${heroku_app.product_service.name}.herokuapp.com"
}
output "order_service_url" {
  value = "https://${heroku_app.order_service.name}.herokuapp.com"
}

output "report_servicep_url" {
  value = "https://${heroku_app.report_service.name}.herokuapp.com"
}
output "gateway_service_url" {
  value = "https://${heroku_app.gateway_service.name}.herokuapp.com"
}