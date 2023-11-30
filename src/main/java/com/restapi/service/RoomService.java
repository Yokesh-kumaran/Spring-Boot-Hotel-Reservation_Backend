package com.restapi.service;

import com.restapi.dto.RoomDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Category;
import com.restapi.model.Order;
import com.restapi.model.Room;
import com.restapi.repository.CategoryRepository;
import com.restapi.repository.OrderRepository;
import com.restapi.repository.RoomRepository;
import com.restapi.request.RoomRequest;
import com.restapi.response.RoomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.stream.Location;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private RoomDto roomDto;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private StorageService storageService;

    @Autowired
    private OrderRepository orderRepository;

    public List<RoomResponse> findAll() {
        List<Room> allRooms = roomRepository.findAll();
        return allRooms.stream()
                .map(roomDto::mapToGetRoom)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<RoomResponse> create(RoomRequest roomRequest) {
        Room room = roomDto.mapToRoom(roomRequest);
        Category category = categoryRepository.findById(roomRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", roomRequest.getCategoryId()));
        room.setCategory(category);
        roomRepository.save(room);
        return findAll();
    }

    @Transactional
    public List<RoomResponse> update(RoomRequest roomRequest) {
        Room room = roomDto.mapToRoom(roomRequest);
        Category category = categoryRepository.findById(roomRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", roomRequest.getCategoryId()));
        room.setCategory(category);
        roomRepository.save(room);
        return findAll();
    }

    public List<RoomResponse> deleteById(Long id) {
        roomRepository.deleteById(id);
        return findAll();
    }

    public List<RoomResponse> getRoomByCategoryId(Long categoryId) {
        List<Room> roomList = roomRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("categoryId", "categoryId", categoryId));
        return roomDto.mapToRoomResponse(roomList);
    }

    public File getFile(Long id) throws IOException {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("id", "id", id));

        Resource resource = storageService.loadFileAsResource(room.getPhoto());

        return resource.getFile();
    }

    public List<RoomResponse> findAllAvailableRooms(String startDate, String endDate) throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date start_date = date.parse(startDate);
        Date end_date = date.parse(endDate);
        List<Order> orders = orderRepository.findByAvailablity(start_date, end_date);
        return roomDto.mapToAvailableRoomResponse(orders);
    }
}
