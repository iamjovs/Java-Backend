package com.example.website.service;

import com.example.website.model.User;
import com.example.website.repository.UserRepository;
import com.example.website.response.LoginMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String addUser(User newUser) {

        User userEntity = new User(
                newUser.getId(),
                newUser.getUsername(),
                this.passwordEncoder.encode(newUser.getPassword())
        );

        userRepository.save(userEntity);

        return userEntity.getUsername();
    }

    public LoginMessage loginUser(User user) {
        String msg = "";
        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1 != null) {
            String password = user.getPassword();
            String encodedPassword = user1.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> userEntity = userRepository.findOneByUsernameAndPassword(user.getUsername(), encodedPassword);
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
