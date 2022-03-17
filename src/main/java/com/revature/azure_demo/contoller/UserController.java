package com.revature.azure_demo.contoller;

import com.revature.azure_demo.models.User;
import com.revature.azure_demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping(value="/")
    public String helloPage() {
        return "These Are Not The Droids You are looking for!";
    }

    @PostMapping(value="/users")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        try {
            User created = userService.addUser(newUser);
            if (created.getId() != 0) {
                return new ResponseEntity<>(created, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/users")
    public ResponseEntity<Iterable<User>> getUsers() {
        return new ResponseEntity<Iterable<User>>(userService.getAllUsers(), HttpStatus.OK);
    }


    @GetMapping(value="/users/id/{id}")
    public ResponseEntity<User> getUsers(@PathVariable("id") int id) {
        Optional<User> found = userService.getUserById(id);
        if(found.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(found.get(), HttpStatus.OK);
    }

    @GetMapping(value="/users/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        Optional<User> found = userService.getUserByUsername(username);
        if(found.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<User>(found.get(), HttpStatus.OK);
    }

    @PutMapping(value="/users/{id}")
    public ResponseEntity<User> getUsers(@RequestBody User change, @PathVariable("id") int id) {
        Optional<User> oldUser = userService.getUserById(id);
        if(oldUser.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        change.setId(id);
        try {
            User created = userService.updateUser(change);
            if (created.getId() != 0) {
                return new ResponseEntity<>(created, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value="users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id){
        Optional<User> oldUser = userService.getUserById(id);
        if(oldUser.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(userService.deleteUser(id))
            return new ResponseEntity<User>(oldUser.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
