package com.example.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class Routes {

    @Bean
    public RouterFunction<ServerResponse> podcastServiceRoute() {
        return route("podcast_service")
                .route(RequestPredicates.path("/api/podcasts/**"),
                      http("http://localhost:8080"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceRoute() {
        return route("user_service")
                .route(RequestPredicates.path("/api/users/**"), 
                      http("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> podcastServiceSwaggerRoute() {
        return route("podcast_service_swagger")
                .route(RequestPredicates.path("/aggregate/podcast-service/v3/api-docs"),
                      http("http://localhost:8080/v3/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userServiceSwaggerRoute() {
        return route("user_service_swagger")
                .route(RequestPredicates.path("/aggregate/user-service/v3/api-docs"),
                      http("http://localhost:8081/v3/api-docs"))
                .build();
    }
}