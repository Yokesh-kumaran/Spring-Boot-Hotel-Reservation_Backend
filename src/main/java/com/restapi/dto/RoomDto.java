package com.restapi.dto;

import com.restapi.model.Order;
import com.restapi.model.Room;
import com.restapi.request.RoomRequest;
import com.restapi.response.RoomResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomDto {

    public Room mapToRoom(RoomRequest roomRequest) {
        Room room = new Room();
        if (roomRequest.getId() != null) {
            room.setId(roomRequest.getId());
        }
        room.setDescription(roomRequest.getDescription());
        room.setPrice(roomRequest.getPrice());
        room.setPhoto(roomRequest.getPhoto());
        return room;
    }

    public RoomResponse mapToGetRoom(Room room) {
        RoomResponse roomResponse = new RoomResponse();
        roomResponse.setId(room.getId());
        roomResponse.setDescription(room.getDescription());
        roomResponse.setPhoto(room.getPhoto());
        roomResponse.setPrice(room.getPrice());
        roomResponse.setCategoryName(room.getCategory().getName());
        return roomResponse;
    }

    public List<RoomResponse> mapToRoomResponse(List<Room> roomList) {
        List<RoomResponse> roomResponseList = new ArrayList<>();
        for (Room room : roomList) {
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setId(room.getId());
            roomResponse.setDescription(room.getDescription());
            roomResponse.setCategoryName(room.getCategory().getName());
            roomResponse.setPrice(room.getPrice());
            roomResponse.setPhoto(room.getPhoto());
            roomResponse.setBedCount(room.getCategory().getAmenity().getBedCount());
            roomResponse.setPowerBackup(room.getCategory().getAmenity().isPowerBackup());
            roomResponse.setAc(room.getCategory().getAmenity().isAc());
            roomResponse.setTv(room.getCategory().getAmenity().isTv());
            roomResponse.setWifi(room.getCategory().getAmenity().isWifi());
            roomResponse.setBreakfast(room.getCategory().getAmenity().isBreakfast());
            roomResponse.setLunch(room.getCategory().getAmenity().isLunch());
            roomResponse.setDinner(room.getCategory().getAmenity().isDinner());
            roomResponse.setParkingFacility(room.getCategory().getAmenity().isParkingFacility());
            roomResponse.setCctvCameras(room.getCategory().getAmenity().isCctvCameras());
            roomResponseList.add(roomResponse);
        }

        return roomResponseList;
    }

    public List<RoomResponse> mapToAvailableRoomResponse(List<Order> orders) {
        List<RoomResponse> roomResponseList = new ArrayList<>();
        for (Order order : orders) {
            RoomResponse roomResponse = new RoomResponse();
            roomResponse.setId(order.getRoom().getId());
            roomResponse.setDescription(order.getRoom().getDescription());
            roomResponse.setCategoryName(order.getRoom().getCategory().getName());
            roomResponse.setPrice(order.getRoom().getPrice());
            roomResponse.setPhoto(order.getRoom().getPhoto());
            roomResponse.setBedCount(order.getRoom().getCategory().getAmenity().getBedCount());
            roomResponse.setPowerBackup(order.getRoom().getCategory().getAmenity().isPowerBackup());
            roomResponse.setAc(order.getRoom().getCategory().getAmenity().isAc());
            roomResponse.setTv(order.getRoom().getCategory().getAmenity().isTv());
            roomResponse.setWifi(order.getRoom().getCategory().getAmenity().isWifi());
            roomResponse.setBreakfast(order.getRoom().getCategory().getAmenity().isBreakfast());
            roomResponse.setLunch(order.getRoom().getCategory().getAmenity().isLunch());
            roomResponse.setDinner(order.getRoom().getCategory().getAmenity().isDinner());
            roomResponse.setParkingFacility(order.getRoom().getCategory().getAmenity().isParkingFacility());
            roomResponse.setCctvCameras(order.getRoom().getCategory().getAmenity().isCctvCameras());
            roomResponseList.add(roomResponse);
        }

        return roomResponseList;
    }
}
