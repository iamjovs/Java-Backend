package com.example.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue
    private UUID playerId;
    private String playerName;
    private BigDecimal playerExp;
    private Integer playerLevel;
    private Integer totalGames;
    private Integer totalWins;
    private Integer totalLoss;
    private BigDecimal winPercentage;
    private BigDecimal lossPercentage;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private Team team;

    public Player(UUID playerId, String playerName, BigDecimal playerExp, Integer playerLevel, Integer totalGames
            , Integer totalWins, Integer totalLoss, BigDecimal winPercentage, BigDecimal lossPercentage){
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerExp = playerExp;
        this.playerLevel = playerLevel;
        this.totalGames = totalGames;
        this.totalWins = totalWins;
        this.totalLoss = totalLoss;
        this.winPercentage = winPercentage;
        this.lossPercentage = lossPercentage;
    }

}
