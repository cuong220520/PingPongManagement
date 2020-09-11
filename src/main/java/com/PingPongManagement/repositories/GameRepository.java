package com.PingPongManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PingPongManagement.models.Game;

public interface GameRepository extends JpaRepository<Game, Integer>{

	List<Game> findByPlayerHome1PlayerId(Integer playerId);
	
	List<Game> findByPlayerHome2PlayerId(Integer playerId);
	
	List<Game> findByPlayerAway1PlayerId(Integer playerId);
	
	List<Game> findByPlayerAway2PlayerId(Integer playerId);
	
	List<Game> findByMatchMatchId(Integer matchId);
}
