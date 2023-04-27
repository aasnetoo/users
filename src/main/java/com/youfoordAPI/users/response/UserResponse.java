package com.youfoordAPI.users.response;

import com.youfoordAPI.users.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
public record UserResponse (String id, String nome, String email, String senha, LocalDateTime dataCriacao, UserEntity.Status status
) {
}
