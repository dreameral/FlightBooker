package com.flightbooker.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudGatewayRouting {

    @Bean
    public RouteLocator configureRoute(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("authId", r->r.path("/auth/**").uri("http://localhost:8762"))
                .route("flightId", r->r.path("/flight/**").uri("http://localhost:8763"))
                .build();
    }

}
