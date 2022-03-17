package com.revature.azure_demo.repositories;

import com.revature.azure_demo.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    public Optional<User> findByUsername(String username);
}
