package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register/guest")
    public ResponseEntity<AuthenticationResponseDTO> registerGuest(@RequestBody GuestRegistrationRequestDTO registrationRequestDTO) {
        AuthenticationResponseDTO response = authenticationService.register(registrationRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthenticationResponseDTO> registerAdmin(@RequestBody AdminRegistrationRequestDTO registrationRequestDTO) {
        AuthenticationResponseDTO response = authenticationService.register(registrationRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/staff")
    public ResponseEntity<AuthenticationResponseDTO> registerStaff(@RequestBody StaffRegistrationRequestDTO registrationRequestDTO) {
        AuthenticationResponseDTO response = authenticationService.register(registrationRequestDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO>  authenticate(@RequestBody LoginRequestDTO loginUserDto) {
        return ResponseEntity.ok(authenticationService.login(loginUserDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        System.out.println(request.getHeader("Authorization"));
        authenticationService.logout(request);
        return ResponseEntity.ok().build();
    }

}
