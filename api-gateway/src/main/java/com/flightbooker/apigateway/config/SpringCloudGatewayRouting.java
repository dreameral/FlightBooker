package com.flightbooker.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {

    @Autowired
    private AuthenticationFilter authenticationFilter;

//    @Bean
//    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("AUTH-MS", r->r.path("/auth/**")
////                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("lb://localhost:8762/"))
//                .route("flightId", r->r.path("/flight/**")
////                        .filters(f -> f.filter(authenticationFilter))
//                        .uri("lb://flightbooker-ms"))
//                .build();
//    }

}
