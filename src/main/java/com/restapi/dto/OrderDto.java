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
            orderResponse.setUserId(order.getAppUser().getId());
            orderResponse.setName(order.getAppUser().getName());
            orderResponse.setUsername(order.getAppUser().getUsername());
            orderResponse.setRoomId(order.getRoom().getId());
            orderResponse.setPrice(order.getRoom().getPrice());
            orderResponse.setCheckinDate(order.getCheckInDate());
            orderResponse.setCheckoutDate(order.getCheckOutDate());
            orderResponse.setCategoryName(order.getRoom().getCategory().getName());
            orderResponseList.add(orderResponse);
        }

        return orderResponseList;
    }
}
