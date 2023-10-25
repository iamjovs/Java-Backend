package com.example.website.model;

import lombok.*;
import org.springframework.lang.Nullable;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;

import javax.persistence.*;

import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {

    @Id
    @GeneratedValue
    private UUID id;
    private BigDecimal Balance;
    private BigDecimal bonusBalance;
    private BigDecimal totalDeposited;
    private BigDecimal totalWithdrawn;
    @Nullable
    private LocalDate lastDeposit;
    @Nullable
    private LocalDate lastWithdraw;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy="wallet")
    private Set<Withdraw> withdraw;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy="wallet")
    private Set<Deposit> deposit;
}
