package com.PingPongManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PingPongManagement.dtos.ResponseMessage;
import com.PingPongManagement.models.TeamAchievementPerRound;
import com.PingPongManagement.services.TeamAchievementPerRoundService;

@RestController
@RequestMapping("/api/team-achievement-per-round")
public class TeamAchievementPerRoundController {

	@Autowired
	private TeamAchievementPerRoundService service;
	
	@GetMapping("/get-by-team/{teamParticipationId}")
	public ResponseEntity<?> getByTeamParticipationId(@PathVariable Integer teamParticipationId) {
		try {
			List<TeamAchievementPerRound> achievements = service.getByTeam(teamParticipationId);
			
			if(achievements.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("This team has no achievements"), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(achievements, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{teamAchievementPerRoundId}")
	public ResponseEntity<?> deleteTeamAchievementPerRound(@PathVariable Integer teamAchievementPerRoundId) {
		try {
			service.deleteTeamAchievementPerRound(teamAchievementPerRoundId);
			
			return new ResponseEntity<>(new ResponseMessage("Delete Success"), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
