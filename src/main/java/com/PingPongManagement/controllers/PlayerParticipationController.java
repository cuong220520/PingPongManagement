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
import com.PingPongManagement.models.PlayerParticipation;
import com.PingPongManagement.services.PlayerParticipationService;

@RestController
@RequestMapping("/api/player-participation")
public class PlayerParticipationController {

	@Autowired
	private PlayerParticipationService playerParticipationService;
	
	@GetMapping("/{teamParticipationId}")
	public ResponseEntity<?> getPlayerParticipationsByTeamParticipationId(@PathVariable Integer teamParticipationId){
		try {
			List<PlayerParticipation> playerParticipations = playerParticipationService.getPlayerParticipationsByTeamParticipationId(teamParticipationId);
			
			if(playerParticipations.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("There is no team assigned to this league"), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(playerParticipations, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> savePlayerParticipation(@Valid @RequestBody PlayerParticipation playerParticipation,
													 BindingResult bindingResult) {
		try {
			playerParticipationService.savePlayerParticipation(playerParticipation);
			
			return new ResponseEntity<>(new ResponseMessage("Save Player Participation successfully!"),
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
	
	/*@DeleteMapping("/delete/{playerParticipationId}")
    public ResponseEntity<?> deletePlayerParticipation(@PathVariable Integer playerParticipationId){
    	try {
			playerParticipationService.deletePlayerParticipation(playerParticipationId);
			return new ResponseEntity<>(new ResponseMessage("Delete Player Participation successfully!"),
                    HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }*/

	@DeleteMapping("/delete/{teamParticipationId}/{playerId}")
	public ResponseEntity<?> deletePlayerParticipation(@PathVariable Integer teamParticipationId,
													   @PathVariable Integer playerId){
		try {
			playerParticipationService.deletePlayerParticipation(teamParticipationId, playerId);
			return new ResponseEntity<>(new ResponseMessage("Delete Player Participation successfully!"),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/")
    public ResponseEntity<?> updatePlayerParticipation(@Valid @RequestBody PlayerParticipation playerParticipation, BindingResult bindingResult) {
    	 try {
             playerParticipationService.updatePlayerParticipation(playerParticipation);
             return new ResponseEntity<>(new ResponseMessage("Update Player Participation successfully!"),
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
	
}
