package com.PingPongManagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PingPongManagement.models.TeamAchievementPerRound;
import com.PingPongManagement.repositories.TeamAchievementPerRoundRepository;

@Service
public class TeamAchievementPerRoundService {

	@Autowired
	private TeamAchievementPerRoundRepository repository;
	
	public List<TeamAchievementPerRound> getByTeam(Integer teamParticipationId){
		return repository.findByTeamTeamParticipationId(teamParticipationId);
	}
	
	public TeamAchievementPerRound saveAchievement(TeamAchievementPerRound teamAchievementPerRound) {
		return repository.save(teamAchievementPerRound);
	}
	
	public void deleteTeamAchievementPerRound(Integer teamAchievementPerRoundId) {
		repository.deleteById(teamAchievementPerRoundId);
	}
}
