package com.restapi.service;

import com.restapi.dto.AuthDto;
import com.restapi.dto.UserDto;
import com.restapi.exception.common.InvalidUserException;
import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.model.Room;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoginRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import com.restapi.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private AuthDto authDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDto userDto;

    public AuthResponse register(RegisterRequest registerRequest) {
        AppUser appUser = authDto.mapToAppUser(registerRequest);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(roleRepository.findByName(Role.USER));
        appUser = userRepository.save(appUser);
        return authDto.mapToAuthResponse(appUser);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        AppUser appUser = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidUserException("Invalid user credentials"));

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
            throw new InvalidUserException("Invalid password");
        }

        return authDto.mapToAuthResponse(appUser);
    }

    public List<UserResponse> findAll() {
        List<AppUser> allUsers = userRepository.findAllUser();
        return allUsers.stream()
                .map(userDto::mapToGetUser)
                .collect(Collectors.toList());
    }

    public List<UserResponse> deleteById(Long id) {
        userRepository.deleteById(id);
        return findAll();
    }
}
