package com.digitalnurture.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserClient userClient;

    @GetMapping
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}/details")
    public Map<String, Object> getOrderWithUser(@PathVariable Long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        UserDto user = userClient.getUser(order.getUserId());

        Map<String, Object> details = new HashMap<>();
        details.put("order", order);
        details.put("user", user);
        return details;
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        userClient.getUser(order.getUserId());
        return orderRepository.save(order);
    }
}
