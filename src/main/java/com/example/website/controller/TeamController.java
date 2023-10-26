package com.example.website.controller;

import com.example.website.model.TeamPlayerBody;
import com.example.website.repository.TeamRepository;
import com.example.website.response.ResponseMessage;
import com.example.website.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user/player/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @PostMapping(path = "/create")
    public ResponseEntity<?> createTeam(@RequestBody TeamPlayerBody teamPlayerBody){
        ResponseMessage responseMessage = teamService.createTeam(teamPlayerBody);
        return ResponseEntity.ok(responseMessage);
    }
}
