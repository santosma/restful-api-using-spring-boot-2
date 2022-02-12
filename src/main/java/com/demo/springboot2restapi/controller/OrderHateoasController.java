package com.demo.springboot2restapi.controller;

import com.demo.springboot2restapi.entities.Order;
import com.demo.springboot2restapi.entities.User;
import com.demo.springboot2restapi.exceptions.UserNotFoundException;
import com.demo.springboot2restapi.repository.OrderRepository;
import com.demo.springboot2restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/hateos/users")
public class OrderHateoasController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping(value = "/{userId}/orders")
    public CollectionModel<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        List<Order> allOrders = optionalUser.get().getOrders();
        CollectionModel<Order> collectionModel = CollectionModel.of(allOrders);
        return collectionModel;
    }
}
