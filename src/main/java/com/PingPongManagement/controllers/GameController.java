package com.PingPongManagement.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PingPongManagement.dtos.ResponseMessage;
import com.PingPongManagement.models.Game;
import com.PingPongManagement.services.GameService;

@RestController
@RequestMapping("/api/game")
public class GameController {

	@Autowired
	private GameService service;

	@GetMapping("/{matchId}")
	public ResponseEntity<?> getGamesByMatchId(@PathVariable Integer matchId) {
		try {
			List<Game> games = service.findByMatch(matchId);
			if (games.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("There is no games for this match"), HttpStatus.OK);
			}

			return new ResponseEntity<>(games, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{playerId}")
	public ResponseEntity<?> getGamesByPlayerId(@PathVariable Integer playerId) {
		try {
			List<Game> games = service.findByPlayerId(playerId);
			
			if(games.isEmpty()) {
				return new ResponseEntity<>(new ResponseMessage("There is no game for this player"), HttpStatus.OK);
			}
			
			return new ResponseEntity<>(games, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/")
	public ResponseEntity<?> saveGame(@Valid @RequestBody Game game, BindingResult bindingResult) {
		System.out.println("controller");
		try {
			service.saveGame(game);
			

			return new ResponseEntity<>(new ResponseMessage("Save Game successfully!"), HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			List<FieldError> errors = bindingResult.getFieldErrors();

			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
		}
	}

}
