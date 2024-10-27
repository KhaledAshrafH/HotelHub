package com.khalouda.hotelhub.model.mapper;

import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.Admin;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface AdminMapper {
    AdminResponseDTO toResponseDTO(Admin admin);
    List<AdminResponseDTO> toResponseDTOs(List<Admin> admins);

    Admin toEntity(AdminUpdateDTO adminUpdateDTO);
    Admin toEntity(AdminRegistrationRequestDTO registrationRequestDTO);
}