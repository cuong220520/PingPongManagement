package com.PingPongManagement.services;

import java.util.List;

import javax.transaction.Transactional;

import com.PingPongManagement.exceptions.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PingPongManagement.models.PlayerParticipation;
import com.PingPongManagement.models.TeamParticipation;
import com.PingPongManagement.repositories.PlayerParticipationRepository;

@Service
public class PlayerParticipationService {

	@Autowired
	private PlayerParticipationRepository playerParticipationRepository;
	
	public PlayerParticipation savePlayerParticipation(PlayerParticipation playerParticipation) {
		return playerParticipationRepository.save(playerParticipation);
	}
	
	public List<PlayerParticipation> getPlayerParticipationsByTeamParticipationId(Integer teamParticipationId) {
		return playerParticipationRepository.findByTeamParticipationTeamParticipationId(teamParticipationId);
	}
	
	public void deletePlayerParticipation(Integer teamParticipationId, Integer playerId) {
		PlayerParticipation playerParticipation =
				playerParticipationRepository.findByTeamParticipationTeamParticipationIdAndPlayerPlayerId(teamParticipationId, playerId)
						.orElseThrow(() -> new AppException("Player Participation not found!"));

		playerParticipationRepository.deleteById(playerParticipation.getPlayerParticipationId());
	}
	
	public PlayerParticipation updatePlayerParticipation(PlayerParticipation playerParticipation) {
		PlayerParticipation existing =
				playerParticipationRepository.findById(playerParticipation.getPlayerParticipationId())
						.orElseThrow(() -> new AppException("Player Participation not found!"));
		existing.setPlayer(playerParticipation.getPlayer());
		existing.setTeamParticipation(playerParticipation.getTeamParticipation());
		return playerParticipationRepository.save(existing);
	}
	
}
