package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.dto.AuthenticationResponseDTO;
import com.khalouda.hotelhub.model.dto.LoginRequestDTO;
import com.khalouda.hotelhub.model.dto.RegistrationRequestDTO;
import com.khalouda.hotelhub.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {
    AuthenticationResponseDTO register(RegistrationRequestDTO input);

    AuthenticationResponseDTO login(LoginRequestDTO input);

    void logout(HttpServletRequest httpServletRequest);

}
