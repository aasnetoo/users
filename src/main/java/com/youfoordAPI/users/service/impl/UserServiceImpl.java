package com.youfoordAPI.users.service.impl;

import com.youfoordAPI.users.dto.UserDTO;
import com.youfoordAPI.users.entity.UserEntity;
import com.youfoordAPI.users.repository.UserRepository;
import com.youfoordAPI.users.request.UserRequest;
import com.youfoordAPI.users.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    @Override
    public Mono<UserEntity> save(UserRequest userRequest) {
        UserEntity newUser = UserEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .senha(userRequest.getSenha())
                .dataCriacao(LocalDateTime.now())
                .status(UserEntity.Status.ATIVO)
                .build();

        return userRepository.save(newUser);
    }

    @Override
    public Flux<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<UserEntity> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<UserEntity.Status> getStatusById(String id) {
        return userRepository.findById(id)
                .flatMap(user -> userRepository.findStatusById(user.getId()))
                .map(UserRepository.UserStatusProjection::getStatus);
    }
}
