package com.PingPongManagement.repositories;

import com.PingPongManagement.dtos.SearchRequest;
import com.PingPongManagement.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    List<Player> findByFirstNameContaining(String term);
}
