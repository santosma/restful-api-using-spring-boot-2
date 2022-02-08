package com.demo.springboot2restapi.controller;

import com.demo.springboot2restapi.exceptions.UserNotFoundException;
import com.demo.springboot2restapi.exceptions.UsernameExistException;
import com.demo.springboot2restapi.model.User;
import com.demo.springboot2restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/create-user")
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder uriCompBuilder) throws UsernameExistException {
        try {
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(
                            uriCompBuilder.
                            path("/user/{id}").
                            buildAndExpand(user.getId()).
                            toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch (UsernameExistException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable("id") Long id) {
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    @PutMapping("/users/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) throws UserNotFoundException {
        try {
            return userService.updateUserById(id, user);
        } catch (UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }

    }

    @DeleteMapping("users/{id}")
    public void deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    //================================================
    //UserName based methods
    //================================================

    @GetMapping("users/username/{userName}")
    public User getByUserName(@PathVariable("userName") String userName) {
        return userService.getByUserName(userName);
    }
}
