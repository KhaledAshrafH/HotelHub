package com.khalouda.hotelhub.service.impl;

import com.khalouda.hotelhub.model.entity.User;
import com.khalouda.hotelhub.repository.UserRepository;
import com.khalouda.hotelhub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
