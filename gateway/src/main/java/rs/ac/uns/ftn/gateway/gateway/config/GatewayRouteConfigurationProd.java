package rs.ac.uns.ftn.gateway.gateway.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@ConditionalOnProperty(
        value = "STAGE",
        havingValue = "PROD"
)
public class GatewayRouteConfigurationProd {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

        String suffixAppUrl = System.getProperty("GATEWAY_SUFFIX");
        //TODO: obrisati print
        System.out.println("UCITAN GATEWAY_SUFFIX: " + suffixAppUrl);

        return builder.routes()

                .route("order",
                        r-> r.path("/order/**")
                                .filters(f->f.rewritePath("/order/(?<path>.*)", "/$\\{path}"))
                                .uri("https://agentorder" + suffixAppUrl + ".herokuapp.com")
                )
                .route("product",
                r-> r.path("/product/**")
                        .filters(f->f.rewritePath("/product/(?<path>.*)", "/$\\{path}"))
                        .uri("https://agentproduct" +suffixAppUrl + ".herokuapp.com")
                )
                .route("report",
                        r-> r.path("/report/**")
                                .filters(f->f.rewritePath("/report/(?<path>.*)", "/$\\{path}"))
                                .uri("https://agentreport" + suffixAppUrl + ".herokuapp.com")
                )
                .route("front",
                        r-> r.path("/")
                                .filters(f->f.rewritePath("/", "/index.html"))
                                .uri("https://agentgateway" + suffixAppUrl + ".herokuapp.com")
                )
                .build();
    }
}