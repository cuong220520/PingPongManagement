package com.PingPongManagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class PlayerAchievementPerRound {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer playerAchievementPerRoundId;
	
	@NotNull
	@NotBlank
	private String round;
	
	@ManyToOne
	@JoinColumn(name = "player", referencedColumnName = "playerId")
	private Player player;
	
	@ManyToOne
	@JoinColumn(name = "league", referencedColumnName = "leagueId")
	private League league;
	
	private Double accumulatedPoints;

	public PlayerAchievementPerRound(Integer playerAchievementPerRoundId, @NotNull @NotBlank String round,
			Player player, League league, Double accumulatedPoints) {
		super();
		this.playerAchievementPerRoundId = playerAchievementPerRoundId;
		this.round = round;
		this.player = player;
		this.league = league;
		this.accumulatedPoints = accumulatedPoints;
	}

	public PlayerAchievementPerRound() {
		super();
	}

	public Integer getPlayerAchievementPerRoundId() {
		return playerAchievementPerRoundId;
	}

	public void setPlayerAchievementPerRoundId(Integer playerAchievementPerRoundId) {
		this.playerAchievementPerRoundId = playerAchievementPerRoundId;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Double getAccumulatedPoints() {
		return accumulatedPoints;
	}

	public void setAccumulatedPoints(Double accumulatedPoints) {
		this.accumulatedPoints = accumulatedPoints;
	}	
}
