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
public class AuthenticationResponseDTO {
    private Long userId;
    private String email;
    private String token;
    private UserRole role;
}
