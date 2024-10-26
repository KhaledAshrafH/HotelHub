package com.khalouda.hotelhub.controller;

import com.khalouda.hotelhub.model.dto.AuthenticationResponseDTO;
import com.khalouda.hotelhub.model.dto.UserResponseDTO;
import com.khalouda.hotelhub.model.entity.User;
import com.khalouda.hotelhub.model.mapper.UserMapper;
import com.khalouda.hotelhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/me")
    public  ResponseEntity<UserResponseDTO> authenticatedUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(userMapper.toResponseDTO(user));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> allUsers() {
        List <User> users = userService.getAllUsers();
        return ResponseEntity.ok(userMapper.toResponseDTOs(users));
    }
}

