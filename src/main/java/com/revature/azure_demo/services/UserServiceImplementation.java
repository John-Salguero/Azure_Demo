package com.revature.azure_demo.services;

import com.revature.azure_demo.models.User;
import com.revature.azure_demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User change) {
        return userRepository.save(change);
    }

    @Override
    public boolean deleteUser(int id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
