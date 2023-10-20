package com.example.website.repo;

import com.example.website.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findOneByUsernameAndPassword(String username, String password);
    UserEntity findByUsername(String username);
}
