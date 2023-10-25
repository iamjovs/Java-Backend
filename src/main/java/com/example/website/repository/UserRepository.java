package com.example.website.repository;

import com.example.website.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findOneByUsernameAndPassword(String username, String password);
    User findByUsername(String username);

    User findUserById(UUID uuid);
}
