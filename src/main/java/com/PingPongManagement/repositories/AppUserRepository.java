package com.PingPongManagement.repositories;

import com.PingPongManagement.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findUserByUsername(String username);
}
