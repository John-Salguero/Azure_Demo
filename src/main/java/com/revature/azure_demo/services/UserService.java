package com.revature.azure_demo.services;

import com.revature.azure_demo.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User addUser(User user);
    Optional<User> getUserById(int id);
    Iterable<User> getAllUsers();
    User updateUser(User change);
    boolean deleteUser(int id);

    Optional<User> getUserByUsername(String username);
}
