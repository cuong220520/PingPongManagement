package com.PingPongManagement.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer gameId;
	
	@ManyToOne
	@JoinColumn(name = "matchId", referencedColumnName = "matchId")
	private Match match;
	
	@ManyToOne
	@JoinColumn(name = "playerHome1", referencedColumnName = "playerId")
	private Player playerHome1;
	
	
	private Double homeAccumulatedPoints1;
	
	
	private String rankingHome1;
	
	@ManyToOne
	@JoinColumn(name = "playerHome2", referencedColumnName = "playerId")
	private Player playerHome2;
	
	
	private Double homeAccumulatedPoints2;
	
	
	private String rankingHome2;
	
	@ManyToOne
	@JoinColumn(name = "playerAway1", referencedColumnName = "playerId")
	private Player playerAway1;
	
	
	private Double awayAccumulatedPoints1;
	
	
	private String rankingAway1;	
	
	@ManyToOne
	@JoinColumn(name = "playerAway2", referencedColumnName = "playerId")
	private Player playerAway2;
	
	
	private Double awayAccumulatedPoints2;
	
	
	private String rankingAway2;
	
	
	private String firstSet;
	
	private String secondSet;
	
	private String thirdSet;
	
	private String fourthSet;
	
	private String fifthSet;
	
	private String advantagePoints;
	
	private String setsResult;
	
	private Double bonusHome1;

	private Double bonusHome2;
	
	private Double bonusAway1;
	
	private Double bonusAway2;

	public Game(Integer gameId, Match match, Player playerHome1, Double homeAccumulatedPoints1, String rankingHome1,
			Player playerHome2, Double homeAccumulatedPoints2, String rankingHome2, Player playerAway1,
			Double awayAccumulatedPoints1, String rankingAway1, Player playerAway2, Double awayAccumulatedPoints2,
			String rankingAway2, String firstSet, String secondSet, String thirdSet, String fourthSet, String fifthSet,
			String advantagePoints, String setsResult, Double bonusHome1, Double bonusHome2, Double bonusAway1,
			Double bonusAway2) {
		super();
		this.gameId = gameId;
		this.match = match;
		this.playerHome1 = playerHome1;
		this.homeAccumulatedPoints1 = homeAccumulatedPoints1;
		this.rankingHome1 = rankingHome1;
		this.playerHome2 = playerHome2;
		this.homeAccumulatedPoints2 = homeAccumulatedPoints2;
		this.rankingHome2 = rankingHome2;
		this.playerAway1 = playerAway1;
		this.awayAccumulatedPoints1 = awayAccumulatedPoints1;
		this.rankingAway1 = rankingAway1;
		this.playerAway2 = playerAway2;
		this.awayAccumulatedPoints2 = awayAccumulatedPoints2;
		this.rankingAway2 = rankingAway2;
		this.firstSet = firstSet;
		this.secondSet = secondSet;
		this.thirdSet = thirdSet;
		this.fourthSet = fourthSet;
		this.fifthSet = fifthSet;
		this.advantagePoints = advantagePoints;
		this.setsResult = setsResult;
		this.bonusHome1 = bonusHome1;
		this.bonusHome2 = bonusHome2;
		this.bonusAway1 = bonusAway1;
		this.bonusAway2 = bonusAway2;
	}

	public Game() {
		super();
	}

	public Integer getGameId() {
		return gameId;
	}

	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Player getPlayerHome1() {
		return playerHome1;
	}

	public void setPlayerHome1(Player playerHome1) {
		this.playerHome1 = playerHome1;
	}

	public Double getHomeAccumulatedPoints1() {
		return homeAccumulatedPoints1;
	}

	public void setHomeAccumulatedPoints1(Double homeAccumulatedPoints1) {
		this.homeAccumulatedPoints1 = homeAccumulatedPoints1;
	}

	public String getRankingHome1() {
		return rankingHome1;
	}

	public void setRankingHome1(String rankingHome1) {
		this.rankingHome1 = rankingHome1;
	}

	public Player getPlayerHome2() {
		return playerHome2;
	}

	public void setPlayerHome2(Player playerHome2) {
		this.playerHome2 = playerHome2;
	}

	public Double getHomeAccumulatedPoints2() {
		return homeAccumulatedPoints2;
	}

	public void setHomeAccumulatedPoints2(Double homeAccumulatedPoints2) {
		this.homeAccumulatedPoints2 = homeAccumulatedPoints2;
	}

	public String getRankingHome2() {
		return rankingHome2;
	}

	public void setRankingHome2(String rankingHome2) {
		this.rankingHome2 = rankingHome2;
	}

	public Player getPlayerAway1() {
		return playerAway1;
	}

	public void setPlayerAway1(Player playerAway1) {
		this.playerAway1 = playerAway1;
	}

	public Double getAwayAccumulatedPoints1() {
		return awayAccumulatedPoints1;
	}

	public void setAwayAccumulatedPoints1(Double awayAccumulatedPoints1) {
		this.awayAccumulatedPoints1 = awayAccumulatedPoints1;
	}

	public String getRankingAway1() {
		return rankingAway1;
	}

	public void setRankingAway1(String rankingAway1) {
		this.rankingAway1 = rankingAway1;
	}

	public Player getPlayerAway2() {
		return playerAway2;
	}

	public void setPlayerAway2(Player playerAway2) {
		this.playerAway2 = playerAway2;
	}

	public Double getAwayAccumulatedPoints2() {
		return awayAccumulatedPoints2;
	}

	public void setAwayAccumulatedPoints2(Double awayAccumulatedPoints2) {
		this.awayAccumulatedPoints2 = awayAccumulatedPoints2;
	}

	public String getRankingAway2() {
		return rankingAway2;
	}

	public void setRankingAway2(String rankingAway2) {
		this.rankingAway2 = rankingAway2;
	}

	public String getFirstSet() {
		return firstSet;
	}

	public void setFirstSet(String firstSet) {
		this.firstSet = firstSet;
	}

	public String getSecondSet() {
		return secondSet;
	}

	public void setSecondSet(String secondSet) {
		this.secondSet = secondSet;
	}

	public String getThirdSet() {
		return thirdSet;
	}

	public void setThirdSet(String thirdSet) {
		this.thirdSet = thirdSet;
	}

	public String getFourthSet() {
		return fourthSet;
	}

	public void setFourthSet(String fourthSet) {
		this.fourthSet = fourthSet;
	}

	public String getFifthSet() {
		return fifthSet;
	}

	public void setFifthSet(String fifthSet) {
		this.fifthSet = fifthSet;
	}

	public String getAdvantagePoints() {
		return advantagePoints;
	}

	public void setAdvantagePoints(String advantagePoints) {
		this.advantagePoints = advantagePoints;
	}

	public String getSetsResult() {
		return setsResult;
	}

	public void setSetsResult(String setsResult) {
		this.setsResult = setsResult;
	}

	public Double getBonusHome1() {
		return bonusHome1;
	}

	public void setBonusHome1(Double bonusHome1) {
		this.bonusHome1 = bonusHome1;
	}

	public Double getBonusHome2() {
		return bonusHome2;
	}

	public void setBonusHome2(Double bonusHome2) {
		this.bonusHome2 = bonusHome2;
	}

	public Double getBonusAway1() {
		return bonusAway1;
	}

	public void setBonusAway1(Double bonusAway1) {
		this.bonusAway1 = bonusAway1;
	}

	public Double getBonusAway2() {
		return bonusAway2;
	}

	public void setBonusAway2(Double bonusAway2) {
		this.bonusAway2 = bonusAway2;
	}
	
	
	
}
