package com.khalouda.hotelhub.service;

import com.khalouda.hotelhub.model.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public interface UtilityService {
    static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
