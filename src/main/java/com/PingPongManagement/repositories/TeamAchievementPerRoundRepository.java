package com.PingPongManagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PingPongManagement.models.TeamAchievementPerRound;

public interface TeamAchievementPerRoundRepository extends JpaRepository<TeamAchievementPerRound, Integer> {

	List<TeamAchievementPerRound> findByTeamTeamParticipationId(Integer teamAchievementPerRoundId);
	
}
