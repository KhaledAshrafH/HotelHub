package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String city;
    private UserRole role;
}
