package com.example.website.service;

import com.example.website.model.Player;
import com.example.website.model.TeamPlayerBody;
import com.example.website.model.User;
import com.example.website.repository.PlayerRepository;
import com.example.website.repository.UserRepository;
import com.example.website.response.ResponseMessage;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PlayerService {
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;

    public PlayerService(UserRepository userRepository, PlayerRepository playerRepository) {
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
    }

    public Player createPlayer(){
        Player player = new Player();

        player.setPlayerId(UUID.randomUUID());
        player.setPlayerLevel(0);
        player.setPlayerExp(BigDecimal.ZERO);
        player.setPlayerName("");
        player.setTotalGames(0);
        player.setTotalWins(0);
        player.setTotalLoss(0);
        player.setWinPercentage(BigDecimal.ZERO);
        player.setLossPercentage(BigDecimal.ZERO);

        return playerRepository.save(player);
    }

    public Player findPlayerById(UUID uuid){
        return playerRepository.findById(uuid).orElse(null);
    }

    public ResponseMessage changeName(TeamPlayerBody teamPlayerBody){
        User user = userRepository.findByUsername(teamPlayerBody.getUserName());

        Player player = findPlayerById(user.getPlayer().getPlayerId());

        player.setPlayerName(teamPlayerBody.getNewName());

        playerRepository.save(player);

        return new ResponseMessage("Change Name Successful", "Player", true);
    }
}
