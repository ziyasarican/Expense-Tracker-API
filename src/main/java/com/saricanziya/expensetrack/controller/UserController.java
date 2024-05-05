package com.saricanziya.expensetrack.controller;

import com.saricanziya.expensetrack.entity.User;
import com.saricanziya.expensetrack.model.UserModel;
import com.saricanziya.expensetrack.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/register")
    public ResponseEntity<User> saveUser(@Valid @RequestBody UserModel userModel){
        return new ResponseEntity<User>(userService.createUser(userModel), HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return new ResponseEntity<User>(userService.getUser(id), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        User tempUser = userService.updateUser(user, id);
        return new ResponseEntity<User>(tempUser, HttpStatus.OK);
    }
}
