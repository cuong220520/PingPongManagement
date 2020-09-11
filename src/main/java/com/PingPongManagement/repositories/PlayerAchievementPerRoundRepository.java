package com.PingPongManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PingPongManagement.models.PlayerAchievementPerRound;

public interface PlayerAchievementPerRoundRepository extends JpaRepository<PlayerAchievementPerRound, Integer> {

	List<PlayerAchievementPerRound> findByPlayerPlayerIdAndLeagueLeagueId(Integer playerId, Integer leagueId);
	
	List<PlayerAchievementPerRound> findByLeagueLeagueId(Integer leagueId);

	List<PlayerAchievementPerRound> findByPlayerPlayerId(Integer playerId);
}
