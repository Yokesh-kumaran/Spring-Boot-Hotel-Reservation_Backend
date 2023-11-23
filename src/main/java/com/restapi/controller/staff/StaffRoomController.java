package com.restapi.controller.staff;

import com.restapi.model.Role;
import com.restapi.model.Room;
import com.restapi.response.RoomResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.RoomService;
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
@RequestMapping("/api/staff/room")
@RolesAllowed(Role.STAFF)
public class StaffRoomController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private RoomService roomService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public ResponseEntity<APIResponse> getAllRooms() {
        List<RoomResponse> roomResponseList = roomService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
