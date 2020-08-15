package com.PingPongManagement.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class League {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer leagueId;
	
	@NotNull(message = "League Name is required!")
	@NotBlank(message = "League Name must not be blank!")
	private String leagueName;
	
	@NotNull(message = "Start Date is required!")
	private Date startDate;
	
	@NotNull(message = "End Date is required!")
	private Date endDate;
	
	private float reward;

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Integer leagueId) {
		this.leagueId = leagueId;
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public float getReward() {
		return reward;
	}

	public void setReward(float reward) {
		this.reward = reward;
	}

	public League(Integer leagueId, String leagueName, Date startDate, Date endDate,
			float reward) {
		this.leagueId = leagueId;
		this.leagueName = leagueName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reward = reward;
	}

	public League() {

	}
}
