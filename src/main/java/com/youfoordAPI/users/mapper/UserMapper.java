package com.youfoordAPI.users.mapper;

import com.youfoordAPI.users.dto.UserDTO;
import com.youfoordAPI.users.entity.UserEntity;
import com.youfoordAPI.users.request.UserRequest;
import com.youfoordAPI.users.response.UserResponse;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userRequestToUserDTO (UserRequest userRequest);

    @Named("mapWithoutSenha")
    @Mapping(target = "senha", ignore = true)
    UserEntity userResponseToUserEntity (UserResponse response);
    @InheritInverseConfiguration
    UserResponse userEntityToUserResponse (UserEntity entity);


}
