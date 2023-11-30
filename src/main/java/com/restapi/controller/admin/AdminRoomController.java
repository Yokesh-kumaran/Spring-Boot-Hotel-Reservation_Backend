package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.request.RoomRequest;
import com.restapi.response.RoomResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.RoomService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/room")
@RolesAllowed(Role.ADMIN)
public class AdminRoomController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private RoomService roomService;

    @Autowired
    private StorageService storageService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllRooms() {
        List<RoomResponse> roomResponseList = roomService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> createRoom(@RequestParam("photo") MultipartFile photo,
                                                  @RequestParam("categoryId") Long categoryId,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("price") Double price) throws IOException {

        String file = storageService.storeFile(photo);
        RoomRequest roomRequest = new RoomRequest();
        roomRequest.setCategoryId(categoryId);
        roomRequest.setPrice(price);
        roomRequest.setPhoto(file);
        roomRequest.setDescription(description);
        List<RoomResponse> roomResponseList = roomService.create(roomRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(roomResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateRoom(@RequestParam("id") Long id,
                                                  @RequestParam("photo") MultipartFile photo,
                                                  @RequestParam("categoryId") Long categoryId,
                                                  @RequestParam("description") String description,
                                                  @RequestParam("price") Double price) throws IOException {
        String file = storageService.storeFile(photo);
        RoomRequest roomRequest = new RoomRequest();
        roomRequest.setId(id);
        roomRequest.setCategoryId(categoryId);
        roomRequest.setPrice(price);
        roomRequest.setPhoto(file);
        roomRequest.setDescription(description);
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

    @GetMapping("/downloadFile/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {

        File file = roomService.getFile(id);

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
