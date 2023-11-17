package com.restapi.dto;

import com.restapi.model.Room;
import com.restapi.request.RoomRequest;
import org.springframework.stereotype.Component;

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
}
