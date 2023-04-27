package com.youfoordAPI.users.controller.router;

import com.youfoordAPI.users.controller.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
@RequiredArgsConstructor
public class UserRouter {
    private final UserHandler handler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route(POST("/usuarios"), handler::save)
                .andRoute(GET("/usuarios"), handler::getAll)
                .andRoute(GET("/usuarios/status/{id}"), handler::getStatusById)
                .andRoute(GET("/usuarios/{id}"), handler::findById);

    }
}