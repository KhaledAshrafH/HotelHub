package com.khalouda.hotelhub.model.mapper;

import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.User;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toResponseDTO(User user);
    List<UserResponseDTO> toResponseDTOs(List<User> users);

    User toEntity(UserCreationDTO userCreationDTO);
    User toEntity(LoginRequestDTO loginRequestDTO);
}
