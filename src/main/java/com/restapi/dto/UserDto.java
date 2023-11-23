package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.response.UserResponse;
import org.h2.engine.User;
import org.springframework.stereotype.Component;

@Component
public class UserDto {
    public UserResponse mapToGetUser(AppUser appUser) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(appUser.getId());
        userResponse.setName(appUser.getName());
        userResponse.setUsername(appUser.getUsername());
        userResponse.setJoinedAt(appUser.getCreatedAt());
        return userResponse;
    }
}
