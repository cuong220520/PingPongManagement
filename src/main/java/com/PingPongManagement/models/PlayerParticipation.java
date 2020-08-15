package com.PingPongManagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class PlayerParticipation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer playerParticipationId;
	
	@ManyToOne
    @JoinColumn(name = "playerId", referencedColumnName = "playerId")
    private Player player;
	
	@ManyToOne
    @JoinColumn(name = "teamParticipationId", referencedColumnName = "teamParticipationId")
	private TeamParticipation teamParticipation;

	public PlayerParticipation(Integer playerParticipationId, Player player, TeamParticipation teamParticipation) {
		this.playerParticipationId = playerParticipationId;
		this.player = player;
		this.teamParticipation = teamParticipation;
	}

	public PlayerParticipation() {

	}

	public Integer getPlayerParticipationId() {
		return playerParticipationId;
	}

	public void setPlayerParticipationId(Integer playerParticipationId) {
		this.playerParticipationId = playerParticipationId;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public TeamParticipation getTeamParticipation() {
		return teamParticipation;
	}

	public void setTeamParticipation(TeamParticipation teamParticipation) {
		this.teamParticipation = teamParticipation;
	}	
	
}
