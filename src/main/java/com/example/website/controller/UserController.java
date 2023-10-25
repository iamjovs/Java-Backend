package com.example.website.controller;

import com.example.website.model.User;
import com.example.website.response.LoginMessage;
import com.example.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public String saveUser(@RequestBody User user){
        String id = userService.addUser(user);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
            LoginMessage loginMessage = userService.loginUser(user);
            return ResponseEntity.ok(loginMessage);
    }
}
