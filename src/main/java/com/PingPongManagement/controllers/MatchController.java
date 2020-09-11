package com.PingPongManagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PingPongManagement.dtos.ResponseMessage;
import com.PingPongManagement.models.Match;
import com.PingPongManagement.models.TeamParticipation;
import com.PingPongManagement.services.MatchService;

@RestController
@RequestMapping("/api/match")
public class MatchController {

	@Autowired
	private MatchService matchService;
	
	@GetMapping("/get-by-team/{teamId}")
	public ResponseEntity<?> getMatchesByTeam(@PathVariable Integer teamId) {
		try {
			List<Match> matches = matchService.findMatchesByTeamId(teamId);
			
			if(matches.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("This team has no matches!"),
                        HttpStatus.OK);
			}
			
			return new ResponseEntity<>(matches, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get-by-league/{leagueId}")
	public ResponseEntity<?> getMatchesByLeague(@PathVariable Integer leagueId) {
		System.out.println("controller");
		try {
			List<Match> matches = matchService.findMatchesByLeagueId(leagueId);
			
			if(matches.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("This league has no matches!"),
                        HttpStatus.OK);
			}
			
			
			
			return new ResponseEntity<>(matches, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/make-schedule/")
	public ResponseEntity<?> generateSchedule(@Valid @RequestBody List<TeamParticipation> teams, BindingResult bindingResult) {
		try {
			List<Match> matches = matchService.generateSchedule(teams);
			
			if(matches.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("Failed to generate matches"),
                        HttpStatus.INTERNAL_SERVER_ERROR); 
			}
			
			return new ResponseEntity<>(matches, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			List<FieldError> errors = bindingResult.getFieldErrors();

			if(!errors.isEmpty()) {
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> saveMatch(@Valid @RequestBody Match match, BindingResult bindingResult) {
		try {
			Match newMatch = matchService.saveMatch(match);
			
			if(newMatch == null) {
				return new ResponseEntity<>(new ResponseMessage("This team has no matches!"),
                        HttpStatus.OK); 
			}
			
			return new ResponseEntity<>(newMatch, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			List<FieldError> errors = bindingResult.getFieldErrors();

			if(!errors.isEmpty()) {
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{matchId}")
	public ResponseEntity<?> deleteMatch(@PathVariable Integer matchId) {
		try {
			matchService.deleteMatchById(matchId);
			
			return new ResponseEntity<>(new ResponseMessage("Delete match successfully!"),
                    HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
}
