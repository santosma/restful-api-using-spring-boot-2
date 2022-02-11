package com.demo.springboot2restapi.controller;

import com.demo.springboot2restapi.entities.Order;
import com.demo.springboot2restapi.entities.User;
import com.demo.springboot2restapi.exceptions.UserNotFoundException;
import com.demo.springboot2restapi.repository.OrderRepository;
import com.demo.springboot2restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(value = "/users")
public class OrderRestController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(value = "/{userId}/orders")
    public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        return optionalUser.get().getOrders();
    }

    @PostMapping("{userid}/orders")
    public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userid);

        if (!userOptional.isPresent())
            throw new UserNotFoundException("User Not Found");

        User user = userOptional.get();
        order.setUser(user);
        return orderRepository.save(order);

    }
}
