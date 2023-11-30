package com.restapi.controller.admin;

import com.restapi.model.Role;
import com.restapi.response.OrderResponse;
import com.restapi.response.RoomResponse;
import com.restapi.response.common.APIResponse;
import com.restapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/admin/order")
@RolesAllowed(Role.ADMIN)
public class AdminOrderController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllOrders() {
        List<OrderResponse> orderList = orderService.getAllOrders();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUsersOrder(@PathVariable Long userId) {
        List<OrderResponse> orderList = orderService.getUserOrders(userId);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse> deleteOrder(@PathVariable Long id) {
        List<OrderResponse> orderResponseList = orderService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(orderResponseList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
