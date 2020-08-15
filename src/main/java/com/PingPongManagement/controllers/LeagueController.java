package com.PingPongManagement.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.PingPongManagement.dtos.SearchRequest;
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
import com.PingPongManagement.models.League;
import com.PingPongManagement.services.LeagueService;

@RestController
@RequestMapping("/api/league")
public class LeagueController {

	@Autowired
	private LeagueService leagueService;
	
	// get all league routes
	@GetMapping("/")
	public ResponseEntity<?> getLeagues() {
		try {
			List<League> leagues = leagueService.getLeagues();
			
			if(leagues.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("There is no player here!"),
                        HttpStatus.OK);
			}
			
			return new ResponseEntity<>(leagues, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// get league route
	@GetMapping("/{leagueId}")
	public ResponseEntity<?> getLeagueById(@PathVariable Integer leagueId) {
		try {
			Optional<League> league = leagueService.getLeague(leagueId);
			
			if (league.isEmpty()) {
                return new ResponseEntity<>(new ResponseMessage("There is no league hear!"),
                        HttpStatus.OK);
            }
			
			return new ResponseEntity<>(league, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//search league route
	@PostMapping("/search")
	public ResponseEntity<?> searchLeagues(@RequestBody SearchRequest searchRequest) {
		try {
			return new ResponseEntity<>(leagueService.searchLeagues(searchRequest), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// save league route
    @PostMapping("/")
    public ResponseEntity<?> saveLeague(@Valid @RequestBody League league, BindingResult bindingResult) {
        try {
            leagueService.saveLeague(league);
            return new ResponseEntity<>(new ResponseMessage("Save league successfully!"),
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

    // delete league route
    @DeleteMapping("/delete/{leagueId}")
    public ResponseEntity<?> deleteLeague(@PathVariable Integer leagueId){
    	try {
			leagueService.deleteLeague(leagueId);
			return new ResponseEntity<>(new ResponseMessage("Delete League successfully!"),
                    HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Server error!"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
}
