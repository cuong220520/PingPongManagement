package com.PingPongManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PingPongManagement.models.Match;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer>{

	List<Match> findByHomeTeamParticipationId(Integer teamId);
	
	List<Match> findByAwayTeamParticipationId(Integer teamId);

	
	
}
