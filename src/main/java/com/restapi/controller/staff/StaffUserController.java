package com.restapi.controller.staff;

import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.response.UserResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/staff/user")
@RolesAllowed(Role.STAFF)
public class StaffUserController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public ResponseEntity<APIResponse> getAllUsers() {
        List<UserResponse> userResponses = userService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(userResponses);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
