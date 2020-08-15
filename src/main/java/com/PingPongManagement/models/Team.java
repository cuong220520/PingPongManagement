package com.PingPongManagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer teamId;
	
	@NotNull(message = "Team Name is required!")
    @NotBlank(message = "Team Name must not be blank!")
	private String teamName;
	
	@NotNull(message = "Team Code is required!")
    @NotBlank(message = "Team Code must not be blank!")
    @Column(unique = true)
	private String teamCode;

	@NotNull(message = "TP is required!")
	@NotBlank(message = "TP must not be blank!")
	private String tP;

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String gettP() {
		return tP;
	}

	public void settP(String tP) {
		this.tP = tP;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
}
