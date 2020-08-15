package com.PingPongManagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class TeamParticipation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teamParticipationId;
	
	@NotNull(message = "Series is required!")
	@NotBlank(message = "Series must not be blank!")
	private String series;

	@Column(name = "`group`")
	private String group;

	private Integer ranking;

	private Integer points;

	@ManyToOne
	@JoinColumn(name = "teamId", referencedColumnName = "teamId")
	private Team team;
	
	@ManyToOne
	@JoinColumn(name = "leagueId", referencedColumnName = "leagueId")
	private League league;

	public TeamParticipation(Integer teamParticipationId, @NotNull String series, @NotNull String group,
			@NotNull int ranking, @NotNull int points, Team team, League league) {
		
		this.teamParticipationId = teamParticipationId;
		this.series = series;
		this.group = group;
		this.ranking = ranking;
		this.points = points;
		this.team = team;
		this.league = league;
	}

	public TeamParticipation() {
		
	}

	public Integer getTeamParticipationId() {
		return teamParticipationId;
	}

	public void setTeamParticipationId(Integer teamParticipationId) {
		this.teamParticipationId = teamParticipationId;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}
}
