package com.PingPongManagement.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PingPongManagement.models.Game;
import com.PingPongManagement.models.PlayerAchievementPerRound;
import com.PingPongManagement.repositories.GameRepository;
import com.PingPongManagement.repositories.PlayerAchievementPerRoundRepository;
import com.PingPongManagement.repositories.PlayerParticipationRepository;

import net.bytebuddy.implementation.attribute.TypeAttributeAppender.ForInstrumentedType.Differentiating;

@Service
public class GameService {

	@Autowired
	private GameRepository repository;
	
	@Autowired
	private PlayerParticipationRepository playerParticipationRepository;

	@Autowired
	private PlayerAchievementPerRoundRepository playerAchievementPerRoundRepository;
	
	public Game saveGame(Game game) {
		
		game.setAwayAccumulatedPoints1(game.getPlayerAway1().getAccumulatedPoint());
		game.setRankingAway1(game.getPlayerAway1().getRanking());
		game.setHomeAccumulatedPoints1(game.getPlayerHome1().getAccumulatedPoint());
		game.setRankingHome1(game.getPlayerHome1().getRanking());
		if(game.getPlayerHome2() != null && game.getPlayerAway2() != null) {
			game.setAwayAccumulatedPoints2(game.getPlayerAway2().getAccumulatedPoint());
			game.setRankingAway2(game.getPlayerAway2().getRanking());
			game.setHomeAccumulatedPoints2(game.getPlayerHome2().getAccumulatedPoint());
			game.setRankingHome2(game.getPlayerHome2().getRanking());
		}
		
		Double difference = 0.0;
		
		if(game.getPlayerAway2() != null && game.getPlayerHome2() != null) {
			difference = game.getPlayerHome1().getUpdatedPoint() + game.getPlayerHome2().getUpdatedPoint() - game.getPlayerAway1().getUpdatedPoint() - game.getPlayerAway2().getUpdatedPoint();
		}else {
			difference = game.getPlayerHome1().getUpdatedPoint() - game.getPlayerAway1().getUpdatedPoint();
		}
		
		String[] possibleResults = {"3/0", "3/1", "3/2", "0/3", "1/3", "2/3"};
		
		int[] possibleDiff = {9999, 238, 213, 188, 163, 138, 113, 88, 63, 38, 13, -12, -37, -62, -87, -112, -137, -162, -187, -212, -237, -999 };
		
		Double[][] awardedPoints = {
				{0.0, 0.0, 0.0, -75.0, -60.0, -40.0},
				{1.5, 1.0, 1.0, -67.5, -54.0, -36.0},
				{1.5, 1.0, 1.0, -60.0, -48.0, -32.0},
				{3.0, 2.5, 1.5, -52.5, -42.0, -28.0},
				{3.0, 2.5, 1.5, -45.0, -36.0, -24.0},
				{4.5, 3.5, 2.5, -37.5, -30.0, -20.0},
				{6.0, 5.0, 3.0, -30.0, -24.0, -16.0},
				{7.5, 6.0, 4.0, -24.0, -19.0, -13.0},
				{9.0, 7.0, 5.0, -19.5, -15.5, -10.5},
				{11.0, 8.5, 5.5, -15.0, -12.0, -8.0},
				{12.0, 10.0, 6.0, 12.0, 10.0, 6.0},
				{15.0, 12.0, 8.0, -11.0, -8.5, -5.5},
				{19.5, 15.5, 10.5, -9.0, -7.0, -5.0},
				{24.0, 19.0, 13.0, -7.5, -6.0, -4.0},
				{30.0, 24.0, 16.0, -6.0, -5.0, -3.0},
				{37.5, 30.0, 20.0, -4.5, -3.5, -2.5},
				{45.0, 36.0, 24.0, -3.0, -2.5, -1.5},
				{52.5, 42.0, 28.0, -3.0, -2.5, -1.5},
				{60.0, 48.0, 32.0, -1.5, -1.0, -1.0},
				{67.5, 54.0, 36.0, -1.5, -1.0, -1.0},
				{75.0, 60.0, 40.0, 0.0, 0.0, 0.0},
		};
		
		for(int i = 0; i < possibleDiff.length - 1; i++) {
			if( difference < possibleDiff[i] && difference >= possibleDiff[i+1]) {
				for(int j = 0; j < possibleResults.length; j++) {
					if(game.getSetsResult().equals(possibleResults[j])){
						game.setBonusHome1(awardedPoints[i][j]);
						game.setBonusAway1((awardedPoints[i][j] * (-1)));
						break;
					}
				}
				break;
			}
		}
		
		if(game.getPlayerAway2() != null && game.getPlayerHome2() != null) {
			game.setBonusHome2(game.getBonusHome1());
			game.setBonusAway2(game.getBonusAway1());
		}
		
		game.getPlayerHome1().setAccumulatedPoint(game.getPlayerHome1().getAccumulatedPoint() + game.getBonusHome1());
		game.getPlayerAway1().setAccumulatedPoint(game.getPlayerAway1().getAccumulatedPoint() + game.getBonusAway1());
		
		if(game.getPlayerAway2() != null && game.getPlayerHome2() != null) {
			game.getPlayerHome2().setAccumulatedPoint(game.getPlayerHome2().getAccumulatedPoint() + game.getBonusHome2());
			game.getPlayerAway2().setAccumulatedPoint(game.getPlayerAway2().getAccumulatedPoint() + game.getBonusAway2());
		}
		
		PlayerAchievementPerRound homeAchievement1 = new PlayerAchievementPerRound();
		PlayerAchievementPerRound awayAchievement1 = new PlayerAchievementPerRound();
		
		homeAchievement1.setLeague(game.getMatch().getHome().getLeague());
		homeAchievement1.setRound(game.getMatch().getRound());
		homeAchievement1.setPlayer(game.getPlayerHome1());
		homeAchievement1.setAccumulatedPoints(game.getPlayerHome1().getAccumulatedPoint());
		
		awayAchievement1.setLeague(game.getMatch().getAway().getLeague());
		awayAchievement1.setRound(game.getMatch().getRound());
		awayAchievement1.setPlayer(game.getPlayerAway1());
		awayAchievement1.setAccumulatedPoints(game.getPlayerAway1().getAccumulatedPoint());
		
		playerAchievementPerRoundRepository.save(homeAchievement1);
		playerAchievementPerRoundRepository.save(awayAchievement1);
		
		if(game.getPlayerAway2() != null && game.getPlayerHome2() != null) {
			
			PlayerAchievementPerRound homeAchievement2 = new PlayerAchievementPerRound();
			PlayerAchievementPerRound awayAchievement2 = new PlayerAchievementPerRound();
			
			awayAchievement2.setLeague(game.getMatch().getAway().getLeague());
			awayAchievement2.setRound(game.getMatch().getRound());
			awayAchievement2.setPlayer(game.getPlayerAway2());
			awayAchievement2.setAccumulatedPoints(game.getPlayerAway2().getAccumulatedPoint());
			
			homeAchievement2.setLeague(game.getMatch().getHome().getLeague());
			homeAchievement2.setRound(game.getMatch().getRound());
			homeAchievement2.setPlayer(game.getPlayerHome2());
			homeAchievement2.setAccumulatedPoints(game.getPlayerHome2().getAccumulatedPoint());
			
			playerAchievementPerRoundRepository.save(homeAchievement2);
			playerAchievementPerRoundRepository.save(awayAchievement2);
			
		}
		
		return repository.save(game);
	}

	public List<Game> saveGames(List<Game> games) {
		return repository.saveAll(games);
	}

	public List<Game> findByMatch(Integer matchId) {
		return repository.findByMatchMatchId(matchId);
	}

	public List<Game> findByPlayerId(Integer playerId) {
		List<Game> allGames = new ArrayList<Game>();
		
		List<Game> gamesAsHome1 = repository.findByPlayerHome1PlayerId(playerId);
		List<Game> gamesAsHome2 = repository.findByPlayerHome2PlayerId(playerId);
		List<Game> gamesAsAway1 = repository.findByPlayerAway1PlayerId(playerId);
		List<Game> gamesAsAway2 = repository.findByPlayerAway2PlayerId(playerId);

		allGames = Stream.concat(allGames.stream(), gamesAsHome1.stream()).collect(Collectors.toList());

		allGames = Stream.concat(allGames.stream(), gamesAsHome2.stream()).collect(Collectors.toList());

		allGames = Stream.concat(allGames.stream(), gamesAsAway1.stream()).collect(Collectors.toList());

		allGames = Stream.concat(allGames.stream(), gamesAsAway2.stream()).collect(Collectors.toList());

		return allGames;		
	}

}
