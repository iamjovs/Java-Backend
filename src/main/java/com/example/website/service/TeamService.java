package com.example.website.service;

import com.example.website.model.*;
import com.example.website.repository.PlayerRepository;
import com.example.website.repository.TeamRepository;
import com.example.website.response.ResponseMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Service
public class TeamService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public TeamService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public ResponseMessage createTeam(TeamPlayerBody teamPlayerBody){
        Player player = playerRepository.findByPlayerName(teamPlayerBody.getUserName());
        UUID teamId = UUID.randomUUID();
        if(!validate(teamPlayerBody.getNewName())){
            Team team = new Team(
                    teamId,
                    teamPlayerBody.getNewName(),
                    teamPlayerBody.getUserName(),
                    0,
                    BigDecimal.ZERO,
                    0,
                    0,
                    0,
                    BigDecimal.ZERO,
                    BigDecimal.ZERO
            );
            player.setTeam(team);
            playerRepository.save(player);
            return new ResponseMessage("Your Team has been Created", "Team", true);
        } else {
            return new ResponseMessage("Team Already Exists", "Team", false);
        }
    }

    public boolean validate(String teamName){
        boolean isValid;
        isValid = teamRepository.findByTeamName(teamName) != null;

        return isValid;
    }

}
