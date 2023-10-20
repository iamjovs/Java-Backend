package com.example.website.controller;

import com.example.website.dto.LoginDTO;
import com.example.website.dto.UserDTO;
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
    public String saveUser(@RequestBody UserDTO userDTO){
        String id = userService.addUser(userDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
            LoginMessage loginMessage = userService.loginUser(loginDTO);
            return ResponseEntity.ok(loginMessage);
    }
}
