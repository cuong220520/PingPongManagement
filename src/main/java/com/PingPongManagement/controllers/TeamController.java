package com.PingPongManagement.controllers;

import java.util.List;
import java.util.Optional;

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
import com.PingPongManagement.models.Team;
import com.PingPongManagement.services.TeamService;

@RestController
@RequestMapping("/api/team")
public class TeamController {

	@Autowired
	private TeamService teamService;
	
	//get all Teams route
	@GetMapping("/")
	public ResponseEntity<?> getTeams() {
		try {
			List<Team> teams = teamService.getTeams();
			
			if (teams.isEmpty()) {
                return new ResponseEntity<>(new ResponseMessage("There is no team here!"),
                        HttpStatus.OK);
            }
			
			return new ResponseEntity<>(teams, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//
	@GetMapping("/{teamId}")
	public ResponseEntity<?> getTeam(@PathVariable Integer teamId) {
		try {
			Optional<Team> team = teamService.getTeamByTeamId(teamId);
			
			if (team.isEmpty()) {
                return new ResponseEntity<>(new ResponseMessage("There is no team with that id!"),
                        HttpStatus.OK);
            }
			
			return new ResponseEntity<>(team, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	 // save team route
    @PostMapping("/")
    public ResponseEntity<?> saveTeam(@Valid @RequestBody Team team, BindingResult bindingResult) {
        try {
            return new ResponseEntity<>(teamService.saveTeam(team),
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
    
    @PutMapping("/")
    public ResponseEntity<?> updateTeam(@Valid @RequestBody Team team, BindingResult bindingResult) {
    	 try {
             teamService.updateTeam(team);
             return new ResponseEntity<>(new ResponseMessage("Update team successfully!"),
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
    
    @DeleteMapping("/delete/{teamId}")
    public ResponseEntity<?> deleteTeam(@PathVariable Integer teamId){
    	try {
			teamService.deleteTeam(teamId);
			return new ResponseEntity<>(new ResponseMessage("Delete team successfully!"),
                    HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
