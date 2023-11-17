package com.restapi.service;

import com.restapi.dto.RoomDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Room;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.RoomRepository;
import com.restapi.request.RoomRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomDto roomDto;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Transactional
    public List<Room> create(RoomRequest roomRequest) {
        Room room = roomDto.mapToRoom(roomRequest);
        Category category = categoryRepository.findById(roomRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", roomRequest.getCategoryId()));
        room.setCategory(category);
        roomRepository.save(room);
        return findAll();
    }

    @Transactional
    public List<Room> update(RoomRequest roomRequest) {
        Room room = roomDto.mapToRoom(roomRequest);
        Category category = categoryRepository.findById(roomRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", roomRequest.getCategoryId()));
        room.setCategory(category);
        roomRepository.save(room);
        return findAll();
    }

    public List<Room> deleteById(Long id) {
        roomRepository.deleteById(id);
        return findAll();
    }
}
