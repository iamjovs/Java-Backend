package com.example.website.controller;

import com.example.website.model.User;
import com.example.website.response.ResponseMessage;
import com.example.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/save")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        ResponseMessage responseMessage = userService.addUser(user);
        return ResponseEntity.ok(responseMessage);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
            ResponseMessage responseMessage = userService.loginUser(user);
            return ResponseEntity.ok(responseMessage);
    }

    @GetMapping(path = "/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
