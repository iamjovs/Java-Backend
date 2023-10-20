package com.example.website.service.impl;

import com.example.website.dto.LoginDTO;
import com.example.website.dto.UserDTO;
import com.example.website.entity.UserEntity;
import com.example.website.repo.UserRepo;
import com.example.website.response.LoginMessage;
import com.example.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {

        UserEntity userEntity = new UserEntity(
                userDTO.getId(),
                userDTO.getUsername(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );

        userRepo.save(userEntity);

        return userEntity.getUsername();
    }

    UserDTO userDTO;
    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        String msg = "";
        UserEntity user1 = userRepo.findByUsername(loginDTO.getUsername());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<UserEntity> userEntity = userRepo.findOneByUsernameAndPassword(loginDTO.getUsername(), encodedPassword);
                if (userEntity.isPresent()) {
                    return new LoginMessage("Login Success", true);

                } else {
                    return new LoginMessage("Login Failed", false);
                }
            } else {
                return new LoginMessage("password Not Match", false);
            }
        }else {
            return new LoginMessage("Email does not exist", false);
        }
    }
}
