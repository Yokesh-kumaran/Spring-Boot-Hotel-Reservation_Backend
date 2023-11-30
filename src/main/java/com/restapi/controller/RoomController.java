package com.restapi.controller;

import com.restapi.model.Role;
import com.restapi.model.Room;
import com.restapi.response.RoomResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {
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

    @GetMapping("/{categoryId}")
    public ResponseEntity<APIResponse> getRoomByCategoryId(@PathVariable Long categoryId){
        List<RoomResponse> roomResponseList = roomService.getRoomByCategoryId(categoryId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/availableRooms/{start_date}/{end_date}")
    public ResponseEntity<APIResponse> getAllAvailableRooms(@PathVariable String start_date,@PathVariable String end_date) throws ParseException {
        List<RoomResponse> availableRoomList = roomService.findAllAvailableRooms(start_date,end_date);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(availableRoomList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
