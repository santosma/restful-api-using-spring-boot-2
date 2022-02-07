package com.demo.springboot2restapi.controller;

import com.demo.springboot2restapi.model.User;
import com.demo.springboot2restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/create-user")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user){
        return userService.updateUserById(id, user);
    }

    @DeleteMapping("users/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }

    //================================================
    //UserName based methods
    //================================================

    @GetMapping("users/username/{userName}")
    public User getByUserName(@PathVariable("userName") String userName){
        return userService.getByUserName(userName);
    }
}
