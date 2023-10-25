package com.example.website.service;

import com.example.website.model.User;
import com.example.website.model.Wallet;
import com.example.website.repository.UserRepository;

import com.example.website.response.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserService {


    private final UserRepository userRepository;

    @Autowired
    private final WalletService walletService;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, WalletService walletService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.walletService = walletService;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseMessage addUser(User newUser) {

        if(!validate(newUser.getUsername())){
            Wallet newWallet = walletService.createWallet();
            User user = new User(
                    newUser.getId(),
                    newUser.getUsername(),
                    this.passwordEncoder.encode(newUser.getPassword()),
                    newUser.getFirstName(),
                    newUser.getMiddleName(),
                    newUser.getLastName(),
                    newUser.getAddress(),
                    newWallet
            );

            userRepository.save(user);

            return new ResponseMessage("Account Created. You may Login", "Register", true);
        } else {
            return new ResponseMessage("Username Already Exists", "Register", false);
        }

    }

    public ResponseMessage loginUser(User user) {
        String msg = "";
        User user1 = userRepository.findByUsername(user.getUsername());
        if (user1 != null) {
            String password = user.getPassword();
            String encodedPassword = user1.getPassword();
            boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> userEntity = userRepository.findOneByUsernameAndPassword(user.getUsername(), encodedPassword);
                if (userEntity.isPresent()) {
                    return new ResponseMessage("Login Success", "Login", true);

                } else {
                    return new ResponseMessage("Login Failed", "Login", false);
                }
            } else {
                return new ResponseMessage("Password Does Not Match", "Login", false);
            }
        }else {
            return new ResponseMessage("Email does not exist", "Login", false);
        }
    }

    public boolean validate(String username){
    boolean isValid;
        if (userRepository.findByUsername(username) != null) isValid = true;
        else isValid = false;

        return isValid;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
