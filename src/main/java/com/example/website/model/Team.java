package com.example.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue
    private UUID teamId;
    private String teamName;
    private String teamOwner;
    private Integer teamLevel;
    private BigDecimal teamExp;
    private Integer totalGames;
    private Integer totalWins;
    private Integer totalLoss;
    private BigDecimal winPercentage;
    private BigDecimal lossPercentage;


}
