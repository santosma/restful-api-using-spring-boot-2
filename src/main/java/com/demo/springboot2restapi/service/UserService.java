package com.demo.springboot2restapi.service;

import com.demo.springboot2restapi.exceptions.UserNameNotFoundException;
import com.demo.springboot2restapi.exceptions.UserNotFoundException;
import com.demo.springboot2restapi.model.User;
import com.demo.springboot2restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) throws UserNotFoundException {
        User existingUser = userRepository.findByUserName(user.getUserName());

        if (existingUser != null) {
            throw new UserNotFoundException("Username already exists");
        }
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id) throws UserNameNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNameNotFoundException("User not found in repository");
        }
        return user;
    }

    public User updateUserById(Long id, User user) throws UserNameNotFoundException {
        Optional<User> possibleUser = userRepository.findById(id);
        if (!possibleUser.isPresent()) {
            throw new UserNameNotFoundException("User not found in repository, cannot update");
        }
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Optional<User> possibleUser = userRepository.findById(id);
        if (!possibleUser.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "User not found in repository, cannot delete");
        }
        userRepository.deleteById(id);
    }

    //================================================
    // UserName based methods
    //================================================
    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    //================================================
    // Exceptions
    //================================================


}
