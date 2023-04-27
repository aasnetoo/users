package com.youfoordAPI.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.youfoordAPI.users.entity.UserEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String senha;
    private LocalDateTime dataCriacao;
    private UserEntity.Status status;

}
