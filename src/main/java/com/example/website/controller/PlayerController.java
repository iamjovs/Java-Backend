package com.example.website.controller;

import com.example.website.model.TeamPlayerBody;
import com.example.website.response.ResponseMessage;
import com.example.website.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping(path = "/change")
    public ResponseEntity<?> changePlayerName(@RequestBody TeamPlayerBody teamPlayerBody){
        ResponseMessage responseMessage = playerService.changeName(teamPlayerBody) ;
        return ResponseEntity.ok(responseMessage);
    }
}
