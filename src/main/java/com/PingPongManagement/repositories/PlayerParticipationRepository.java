package com.PingPongManagement.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PingPongManagement.models.PlayerParticipation;

public interface PlayerParticipationRepository extends JpaRepository<PlayerParticipation, Integer>{

	List<PlayerParticipation> findByTeamParticipationTeamParticipationId(Integer teamParticipationId);

	Optional<PlayerParticipation> findByTeamParticipationTeamParticipationIdAndPlayerPlayerId(Integer teamParticipationId,
																		 Integer playerId);
}
