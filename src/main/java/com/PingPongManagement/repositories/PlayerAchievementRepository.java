package com.PingPongManagement.repositories;

import com.PingPongManagement.models.PlayerAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerAchievementRepository extends JpaRepository<PlayerAchievement, Integer> {
    List<PlayerAchievement> findByPlayerPlayerId(Integer playerId);
}
