package com.restapi.controller.admin;

import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.response.RoomResponse;
import com.restapi.response.UserResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
@RolesAllowed(Role.ADMIN)
public class AdminUserController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllUsers() {
        List<UserResponse> userResponses = userService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Long id) {
        List<UserResponse> userResponses = userService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
