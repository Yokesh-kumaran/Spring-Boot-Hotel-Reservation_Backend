package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.model.Room;
import com.restapi.request.CategoryRequest;
import com.restapi.request.RoomRequest;
import com.restapi.response.CategoryResponse;
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
        List<Room> roomList = roomService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<APIResponse> createRoom(@Valid @RequestBody RoomRequest roomRequest) {
        List<Room> roomList = roomService.create(roomRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateRoom(@Valid @RequestBody RoomRequest roomRequest) {
        List<Room> roomList = roomService.update(roomRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteRoom(@PathVariable Long id) {
        List<Room> roomList = roomService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
