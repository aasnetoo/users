package com.youfoordAPI.users.controller.handler;

import com.youfoordAPI.users.dto.UserDTO;
import com.youfoordAPI.users.entity.UserEntity;
import com.youfoordAPI.users.mapper.UserMapper;
import com.youfoordAPI.users.request.UserRequest;
import com.youfoordAPI.users.response.UserResponse;
import com.youfoordAPI.users.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserHandler {

    private final UserServiceImpl userService;
    private final UserMapper mapper;

    public Mono<ServerResponse> save(ServerRequest request) {

        return request.bodyToMono(UserRequest.class)
                .flatMap(userService::save)
                .flatMap(response -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(response)));

    }
    public Mono<ServerResponse> getAll(ServerRequest request) {
        Flux<UserResponse> userResponses = userService
                .getAll()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .nome(user.getName())
                        .email(user.getEmail())
                        .dataCriacao(user.getDataCriacao())
                        .status(user.getStatus())
                        .build());

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(userResponses, UserResponse.class));
    }

    public Mono<ServerResponse> findById(ServerRequest request) {

        String id = request.pathVariable("id");
        Mono<UserResponse> responseMono = userService.findById(id)
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getSenha(),
                        user.getDataCriacao(),
                        user.getStatus()
                ));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters
                        .fromPublisher(responseMono, UserResponse.class));

    }

    public Mono<ServerResponse> getStatusById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<UserEntity.Status> statusMono = userService.getStatusById(id);
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(statusMono, UserEntity.Status.class));
    }

}
