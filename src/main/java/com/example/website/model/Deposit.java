package com.example.website.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deposit")
public class Deposit {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="walletId", referencedColumnName = "id")
    private Wallet wallet;
    @Id
    @GeneratedValue
    private UUID depositId;
    private BigDecimal amount;
    private LocalDate date;
}
