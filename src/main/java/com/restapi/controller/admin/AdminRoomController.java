package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.model.Room;
import com.restapi.request.CategoryRequest;
import com.restapi.request.RoomRequest;
import com.restapi.response.CategoryResponse;
import com.restapi.response.RoomResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/room")
@RolesAllowed(Role.ADMIN)
public class AdminRoomController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private RoomService roomService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllRooms() {
        List<RoomResponse> roomResponseList = roomService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createRoom(@Valid @RequestBody RoomRequest roomRequest) {
        List<RoomResponse> roomResponseList = roomService.create(roomRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateRoom(@Valid @RequestBody RoomRequest roomRequest) {
        List<RoomResponse> roomResponseList = roomService.update(roomRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteRoom(@PathVariable Long id) {
        List<RoomResponse> roomResponseList = roomService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
