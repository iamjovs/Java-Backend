package com.example.website.repository;

import com.example.website.model.Withdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, UUID> {
}