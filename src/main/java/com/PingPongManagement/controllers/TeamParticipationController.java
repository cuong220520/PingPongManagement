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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PingPongManagement.dtos.ResponseMessage;
import com.PingPongManagement.models.TeamParticipation;
import com.PingPongManagement.services.TeamParticipationService;

@RestController
@RequestMapping("/api/team-participation")
public class TeamParticipationController {

	@Autowired
	private TeamParticipationService teamParticipationService;
	
	@GetMapping("/get-by-league/{leagueId}")
	public ResponseEntity<?> getTeamsByLeagueId(@PathVariable Integer leagueId){
		try {
			List<TeamParticipation> teamParticipations = teamParticipationService.getTeamsByLeagueId(leagueId);
			
			if(teamParticipations.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("There is no team assigned to this league"), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(teamParticipations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/get-by-team/{teamId}")
	public ResponseEntity<?> getLeaguesByTeamId(@PathVariable Integer teamId){
		try {
			List<TeamParticipation> teamParticipations = teamParticipationService.getLeaguesByTeamId(teamId);
			
			if(teamParticipations.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("This team hasn't played any league"), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(teamParticipations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> saveTeamParticipation(@Valid @RequestBody TeamParticipation teamParticipation,
												   BindingResult bindingResult) {
		try {
			return new ResponseEntity<>(teamParticipationService.saveTeamParticipation(teamParticipation),
					HttpStatus.OK);
		} catch (Exception e) {
			List<FieldError> errors = bindingResult.getFieldErrors();

			if (!errors.isEmpty()) {
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{leagueId}/{teamId}")
	public ResponseEntity<?> getTeamParticipationByTeamIdAndLeagueId(@PathVariable Integer leagueId,
																  @PathVariable Integer teamId) {
		try {
			return new ResponseEntity<>(teamParticipationService.getTeamParticipationByLeagueIdAndTeamId(leagueId, teamId),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get-players/{leagueId}/{teamId}")
	public ResponseEntity<?> getPlayerParticipationByTeamIdAndLeagueId(@PathVariable Integer leagueId, @PathVariable Integer teamId) {
		try {
			return new ResponseEntity<>(teamParticipationService.getPlayersParticipationByLeagueIdAndTeamId(leagueId, teamId),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/save-multiple")
	public ResponseEntity<?> saveTeamParticipations(@Valid @RequestBody List<TeamParticipation> teamParticipations, BindingResult bindingResult) {
		try {
			teamParticipationService.saveTeamParticipations(teamParticipations);
			
			return new ResponseEntity<>(new ResponseMessage("Save Team Participations successfully!"),
                    HttpStatus.OK);
		} catch (Exception e) {
			List<FieldError> errors = bindingResult.getFieldErrors();

			if (!errors.isEmpty()) {
				return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			}

			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@PutMapping("/")
    public ResponseEntity<?> updateTeamParticipation(@Valid @RequestBody TeamParticipation teamParticipation, BindingResult bindingResult) {
    	 try {
             teamParticipationService.updateTeamParticipation(teamParticipation);
             return new ResponseEntity<>(new ResponseMessage("Update Team Participation successfully!"),
                     HttpStatus.OK);
         } catch (Exception e) {
             List<FieldError> errors = bindingResult.getFieldErrors();

             return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
         }
    }*/
    
    @DeleteMapping("/delete/{teamParticipationId}")
    public ResponseEntity<?> deleteTeamParticipation(@PathVariable Integer teamParticipationId){
    	try {
			teamParticipationService.deleteTeamParticipation(teamParticipationId);
			return new ResponseEntity<>(new ResponseMessage("Delete Team Participation successfully!"),
                    HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
}
