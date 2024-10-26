package com.khalouda.hotelhub.model.dto;

import com.khalouda.hotelhub.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private UserRole role;
}
