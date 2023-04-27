package com.youfoordAPI.users.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "users")
public class UserEntity {

    @Id
    public String id;
    private String name;
    private String email;
    private String senha;
    private LocalDateTime dataCriacao;
    private Status status;

    public enum Status {
        DESATIVO, ATIVO;
    }

}
