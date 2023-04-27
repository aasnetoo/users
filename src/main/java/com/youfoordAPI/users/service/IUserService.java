package com.youfoordAPI.users.service;

import com.youfoordAPI.users.dto.UserDTO;
import com.youfoordAPI.users.entity.UserEntity;
import com.youfoordAPI.users.request.UserRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {

    Mono<UserEntity> save(UserRequest userRequest);

    Flux<UserEntity> getAll();
    Mono<UserEntity> findById(String id);
    Mono<UserEntity.Status> getStatusById(String id);


}
