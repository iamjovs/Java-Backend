package com.example.website.repository;

import com.example.website.model.Team;
import com.example.website.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
    Team findByTeamName(String teamName);
}
