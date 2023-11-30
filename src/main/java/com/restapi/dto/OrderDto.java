package com.restapi.dto;

import com.restapi.model.Order;
import com.restapi.response.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDto {

    public List<OrderResponse> mapToOrderResponse(List<Order> orderList) {
        List<OrderResponse> orderResponseList = new ArrayList<>();

        for (Order order : orderList) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setRoomId(order.getRoom().getId());
            orderResponse.setFirstName(order.getFirstName());
            orderResponse.setLastName(order.getLastName());
            orderResponse.setCheckInDate(order.getCheckInDate());
            orderResponse.setCheckOutDate(order.getCheckOutDate());
            orderResponse.setPhoneNumber(order.getPhoneNumber());
            orderResponse.setAdults(order.getAdults());
            orderResponse.setChildren(order.getChildren());
            orderResponse.setRoomPrice(order.getRoom().getPrice());
            orderResponse.setCategoryId(order.getRoom().getCategory().getId());
            orderResponseList.add(orderResponse);
        }
        return orderResponseList;
    }
}
