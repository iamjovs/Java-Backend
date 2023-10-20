package com.example.website.service;


import com.example.website.dto.LoginDTO;
import com.example.website.dto.UserDTO;
import com.example.website.response.LoginMessage;

public interface UserService {
    String addUser(UserDTO userDTO);

    LoginMessage loginUser(LoginDTO loginDTO);
}
