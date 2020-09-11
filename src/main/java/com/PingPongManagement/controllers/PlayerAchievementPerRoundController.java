package com.PingPongManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PingPongManagement.dtos.ResponseMessage;
import com.PingPongManagement.models.PlayerAchievementPerRound;
import com.PingPongManagement.services.PlayerAchievementPerRoundService;

@RestController
@RequestMapping("/api/player-achievement-per-round")
public class PlayerAchievementPerRoundController {

	@Autowired
	private PlayerAchievementPerRoundService service;
	
	@GetMapping("/get-by-player/{playerId}")
	public ResponseEntity<?> getByPlayer(@PathVariable Integer playerId) {
		try {
			List<PlayerAchievementPerRound> achievements = service.getByPlayer(playerId);
			
			if(achievements.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("This player has no achievements"), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(achievements, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get-by-league/{leagueId}")
	public ResponseEntity<?> getByLeague(@PathVariable Integer leagueId) {
		try {
			List<PlayerAchievementPerRound> achievements = service.getByLeague(leagueId);
			
			if(achievements.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("This league has no player achievements"), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(achievements, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
