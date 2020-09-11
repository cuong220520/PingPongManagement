package com.PingPongManagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PingPongManagement.models.League;
import com.PingPongManagement.models.PlayerAchievementPerRound;
import com.PingPongManagement.repositories.PlayerAchievementPerRoundRepository;

@Service
public class PlayerAchievementPerRoundService {

	@Autowired
	private PlayerAchievementPerRoundRepository repository;
	
	/*
	 * public PlayerAchievementPerRound saveAchievement(PlayerAchievementPerRound
	 * playerAchievementPerRound) { League league =
	 * playerAchievementPerRound.getPlayer().getTeamParticipation().getLeague();
	 * playerAchievementPerRound.setLeague(league); return
	 * repository.save(playerAchievementPerRound); }
	 */
	
	public void deleteAchievementPerRound(Integer playerAchievementPerRoundId) {
		repository.deleteById(playerAchievementPerRoundId);
	}
	
	public List<PlayerAchievementPerRound> getByPlayer(Integer playerId) {
		return repository.findByPlayerPlayerId(playerId);
	}
	
	public List<PlayerAchievementPerRound> getByLeague(Integer leagueId) {
		return repository.findByLeagueLeagueId(leagueId);
	}
	
	public List<PlayerAchievementPerRound> getByPlayerAndLeague(Integer leagueId, Integer playerId) {
		return repository.findByPlayerPlayerIdAndLeagueLeagueId(playerId, leagueId);
	}
	
}
