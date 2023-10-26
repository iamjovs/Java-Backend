package com.example.website.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;
}
