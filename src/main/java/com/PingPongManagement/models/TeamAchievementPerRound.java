package com.PingPongManagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TeamAchievementPerRound {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teamAchievementPerRoundId;
	
	@ManyToOne
	@JoinColumn(name = "team", referencedColumnName = "teamParticipationId")
	private TeamParticipation team;
	
	private String round;
	
	private Double totalPoints;
	
	private String gameResult;
	
	private String setsResult;

	public TeamAchievementPerRound(Integer teamAchievementPerRoundId, TeamParticipation team, String round,
			Double totalPoints, String gameResult, String setsResult) {
		super();
		this.teamAchievementPerRoundId = teamAchievementPerRoundId;
		this.team = team;
		this.round = round;
		this.totalPoints = totalPoints;
		this.gameResult = gameResult;
		this.setsResult = setsResult;
	}

	public TeamAchievementPerRound() {
		super();
	}

	public Integer getTeamAchievementPerRoundId() {
		return teamAchievementPerRoundId;
	}

	public void setTeamAchievementPerRoundId(Integer teamAchievementPerRoundId) {
		this.teamAchievementPerRoundId = teamAchievementPerRoundId;
	}

	public TeamParticipation getTeam() {
		return team;
	}

	public void setTeam(TeamParticipation team) {
		this.team = team;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public Double getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Double totalPoints) {
		this.totalPoints = totalPoints;
	}

	public String getGameResult() {
		return gameResult;
	}

	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}

	public String getSetsResult() {
		return setsResult;
	}

	public void setSetsResult(String setsResult) {
		this.setsResult = setsResult;
	}
}
