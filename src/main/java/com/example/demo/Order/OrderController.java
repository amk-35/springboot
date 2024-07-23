package com.example.demo.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity createOrder(){
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setTotal(19.9);
        orderRepository.save(order);
        System.out.println(order.getId());
        return ResponseEntity.ok(order);

    }
}
