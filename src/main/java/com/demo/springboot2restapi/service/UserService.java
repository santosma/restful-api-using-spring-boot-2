package com.demo.springboot2restapi.service;

import com.demo.springboot2restapi.model.User;
import com.demo.springboot2restapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public User updateUserById(Long id, User user){
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        if(userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
    }

    //================================================
    //UserName based methods
    //================================================
    public User getByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
