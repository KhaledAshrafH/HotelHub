package com.khalouda.hotelhub.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khalouda.hotelhub.exception.HotelNotFoundException;
import com.khalouda.hotelhub.model.dto.*;
import com.khalouda.hotelhub.model.entity.*;
import com.khalouda.hotelhub.model.enums.NotificationType;
import com.khalouda.hotelhub.model.enums.TokenType;
import com.khalouda.hotelhub.model.mapper.AdminMapper;
import com.khalouda.hotelhub.model.mapper.GuestMapper;
import com.khalouda.hotelhub.model.mapper.StaffMapper;
import com.khalouda.hotelhub.model.mapper.UserMapper;
import com.khalouda.hotelhub.repository.HotelRepository;
import com.khalouda.hotelhub.repository.TokenRepository;
import com.khalouda.hotelhub.repository.UserRepository;
import com.khalouda.hotelhub.service.AuthenticationService;
import com.khalouda.hotelhub.service.NotificationService;
import com.khalouda.hotelhub.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final GuestMapper guestMapper;
    private final AdminMapper adminMapper;
    private final StaffMapper staffMapper;
    private final TokenRepository tokenRepository;
    private final HotelRepository hotelRepository;
    private final JwtTokenUtil jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final NotificationService notificationService;
    @Override
    public AuthenticationResponseDTO register(RegistrationRequestDTO input) {
        User user;
        switch (input.getRole()) {
            case GUEST:
                user= guestMapper.toEntity((GuestRegistrationRequestDTO) input);
                break;
            case ADMIN:
                user= adminMapper.toEntity((AdminRegistrationRequestDTO) input);
                break;
            case STAFF:
                Staff staff = staffMapper.toEntity((StaffRegistrationRequestDTO) input);
                staff.setHotel(hotelRepository.findById(((StaffRegistrationRequestDTO) input).getHotelId()).orElseThrow(()-> new HotelNotFoundException("hotel not found")));
                user = staff;
                break;
            default:
                throw new IllegalArgumentException("Invalid user role");
        }

        user.setEmail(input.getEmail());
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(input.getRole());
        user.setCity(input.getCity());

        User savedUser = userRepository.save(user);

        NotificationCreationDTO notification = NotificationCreationDTO.builder()
                .userId(savedUser.getId())
                .message("Welcome "+savedUser.getFirstName() + " " + savedUser.getLastName()+", You enlighten the app!")
                .type(NotificationType.REGISTRATION_SUCCESS)
                .build();
        notificationService.sendNotification(notification);

        String jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponseDTO.builder()
                .userId(savedUser.getId())
                .email(savedUser.getEmail())
                .token(jwtToken)
                .role(savedUser.getRole())
                .build();
    }

    @Override
    public AuthenticationResponseDTO login(LoginRequestDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        User user = userRepository.findByEmail(input.getEmail())
                .orElseThrow(()->
                        new UsernameNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponseDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .token(jwtToken)
                .role(user.getRole())
                .build();
    }

    @Override
    public void logout(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("From service : " + authHeader);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            final String token = authHeader.substring(7);
            var user = userRepository.findByEmail(jwtService.extractUsername(token))
                    .orElseThrow();
            revokeAllUserTokens(user);
        }
    }


    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUserId(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponseDTO.builder()
                        .userId(user.getId())
                        .email(user.getEmail())
                        .token(accessToken)
                        .role(user.getRole())
                        .build();

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
