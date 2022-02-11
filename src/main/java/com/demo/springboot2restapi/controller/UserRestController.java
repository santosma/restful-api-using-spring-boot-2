package com.demo.springboot2restapi.controller;

import com.demo.springboot2restapi.exceptions.UserNameNotFoundException;
import com.demo.springboot2restapi.exceptions.UserNotFoundException;
import com.demo.springboot2restapi.entities.User;
import com.demo.springboot2restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@Validated
@RequestMapping(value = "/users")
public class UserRestController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder uriCompBuilder) throws UserNotFoundException {
        try {
            userService.createUser(user);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriCompBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        } catch(UserNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @GetMapping("{id}")
    public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
        try {
            return userService.getUserById(id);
        } catch (UserNameNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) throws UserNameNotFoundException {
        try {
            return userService.updateUserById(id, user);
        } catch (UserNameNotFoundException ex) {
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

    @GetMapping("/username/{userName}")
    public User getByUserName(@PathVariable("userName") String userName) throws UserNameNotFoundException {
        User user = userService.getByUserName(userName);
        if (user == null){
            throw new UserNameNotFoundException("UserName: " + userName + " not found nor exists");
        }
        return user;
    }
}
