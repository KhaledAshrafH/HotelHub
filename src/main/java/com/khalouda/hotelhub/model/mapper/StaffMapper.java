package com.khalouda.hotelhub.model.mapper;


import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.Staff;
import com.khalouda.hotelhub.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface StaffMapper {
    StaffResponseDTO toResponseDTO(Staff staff);
    List<StaffResponseDTO> toResponseDTOs(List<Staff> staffs);
    StaffUpdateDTO toUpdateDTO(Staff staff);

    Staff toEntity(StaffUpdateDTO staffUpdateDTO);
    Staff toEntity(StaffRegistrationRequestDTO registrationRequestDTO);
}
