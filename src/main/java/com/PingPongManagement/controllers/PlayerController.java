package com.PingPongManagement.controllers;

import com.PingPongManagement.dtos.ResponseMessage;
import com.PingPongManagement.dtos.SearchRequest;
import com.PingPongManagement.dtos.UploadFileResponse;
import com.PingPongManagement.exceptions.AppException;
import com.PingPongManagement.models.Player;
import com.PingPongManagement.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/player/")
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    // save player route
    @PostMapping("/")
    public ResponseEntity<?> savePlayer(@Valid @RequestBody Player player, BindingResult bindingResult) {
        try {
            playerService.savePlayer(player);
            return new ResponseEntity<>(new ResponseMessage("Save player successfully!"),
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

    // get all players route
    @GetMapping("/")
    public ResponseEntity<?> getPlayers() {
        try {
            List<Player> players = playerService.getPlayers();

            if (players.isEmpty()) {
                return new ResponseEntity<>(new ResponseMessage("There is no player here!"),
                        HttpStatus.OK);
            }

            return new ResponseEntity<>(players, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get player by playerId route
    @GetMapping("/{playerId}")
    public ResponseEntity<?> getPlayer(@PathVariable Integer playerId) {
        try {
            Optional<Player> player = playerService.getPlayer(playerId);

            if (player.isEmpty()) {
                return new ResponseEntity<>(new ResponseMessage("There is no player here!"),
                        HttpStatus.OK);
            }

            return new ResponseEntity<>(player, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // search players by firstName route
    @PostMapping("/search")
    public ResponseEntity<?> searchPlayer(@RequestBody SearchRequest term) {
        try {
            System.out.println(playerService.searchPlayers(term));
            return new ResponseEntity<>(playerService.searchPlayers(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete player by playerId route
    @DeleteMapping("/delete/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable Integer playerId) {
        try {
            playerService.deletePlayer(playerId);
            return new ResponseEntity<>(new ResponseMessage("Delete player successfully!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update player achievement route
    @PutMapping("/update-achievement/{playerId}")
    public ResponseEntity<?> updatePlayerAchievement(@PathVariable Integer playerId) {
        try {
            playerService.updatePlayerAchievement(playerId);

            return new ResponseEntity<>(new ResponseMessage("Update player achievement " +
                    "successfully!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update all players achievement route
    @PutMapping("/update-achievement")
    public ResponseEntity<?> updatePlayersAchievement() {
        try {
            playerService.updatePlayersAchievement();

            return new ResponseEntity<>(new ResponseMessage("Update all players achievement " +
                    "successfully!"),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // get player achievements
    @GetMapping("/achievement/{playerId}")
    public ResponseEntity<?> getPlayerAchievements(@PathVariable Integer playerId) {
        try {
            return new ResponseEntity<>(playerService.getPlayerAchievements(playerId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static String UPLOAD_DIR = "uploads";

    // upload file route
    @PostMapping("/upload-file")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
                                    HttpServletRequest request) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath =
                    request.getServletContext().getContextPath() + UPLOAD_DIR + File.separator + fileName;
            saveFile(file.getInputStream(), filePath);
            return new ResponseEntity<>(new UploadFileResponse(fileName, filePath), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // save file function
    public void saveFile(InputStream inputStream, String filePath) {
        try {
            OutputStream outputStream = new FileOutputStream(new File(filePath));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new AppException("Save file error!", e);
        }
    }
}
