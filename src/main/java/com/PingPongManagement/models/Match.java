package com.PingPongManagement.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "`match`")
public class Match {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer matchId;
	
	@ManyToOne
	@JoinColumn(name = "home", referencedColumnName = "teamParticipationId")
	private TeamParticipation home;
	
	@ManyToOne
	@JoinColumn(name = "away", referencedColumnName = "teamParticipationId")
	private TeamParticipation away;
	
	@NotNull(message = "must include round!")
	@NotBlank(message = "must include round")
	private String round;
	
	
	private Date date;
	
	private Double homeCurrentPoints;
	
	private Double awayCurrentPoints;
	
	private Double bonusPointsHome;
	
	private Double bonusPointsAway;
	
	private String gameResults;
	
	private String setResults;

	public Match(Integer matchId, TeamParticipation home, TeamParticipation away,
			@NotNull(message = "must include round!") @NotBlank(message = "must include round") String round,
			@NotNull(message = "must include date") Date date,
			String gameResults, String setResults) {
		super();
		this.matchId = matchId;
		this.home = home;
		this.away = away;
		this.round = round;
		this.date = date;
		this.gameResults = gameResults;
		this.setResults = setResults;
	}

	public Match() {
		super();
	}

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public TeamParticipation getHome() {
		return home;
	}

	public void setHome(TeamParticipation home) {
		this.home = home;
	}

	public TeamParticipation getAway() {
		return away;
	}

	public void setAway(TeamParticipation away) {
		this.away = away;
	}

	public String getRound() {
		return round;
	}

	public void setRound(String round) {
		this.round = round;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGameResults() {
		return gameResults;
	}

	public void setGameResults(String gameResults) {
		this.gameResults = gameResults;
	}

	public String getSetResults() {
		return setResults;
	}

	public void setSetResults(String setResults) {
		this.setResults = setResults;
	}	
}
