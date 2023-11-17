package com.restapi.service;

import com.restapi.dto.OrderDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Order;
import com.restapi.model.Room;
import com.restapi.repository.OrderRepository;
import com.restapi.repository.RoomRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.OrderRequest;
import com.restapi.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private OrderDto orderDto;

    public List<OrderResponse> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderDto.mapToOrderResponse(orderList);
    }

    public List<OrderResponse> getUserOrders(Long userId) {
        List<Order> orderList = orderRepository.findUserOrder(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));
        return orderDto.mapToOrderResponse(orderList);
    }


    @Transactional
    public Order placeOrder(OrderRequest orderRequest) {
        AppUser appUser = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", orderRequest.getUserId()));

        Room room = roomRepository.findById(orderRequest.getRoomId())
                .orElseThrow(() -> new ResourceNotFoundException("roomId", "roomId", orderRequest.getRoomId()));

        Order order = new Order();
        order.setAppUser(appUser);
        order.setRoom(room);
        order.setCheckInDate(order.getCheckInDate());
        order.setCheckOutDate(order.getCheckOutDate());

        order = orderRepository.save(order);
        return order;
    }
}
