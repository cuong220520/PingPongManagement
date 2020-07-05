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

@RestController
@RequestMapping("/api/player")
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

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
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

            return new ResponseEntity<>(players, HttpStatus.OK);
        } catch (Exception e) {
            throw new AppException("Get all players error!", e);
        }
    }

    // get player by playerId route
    @GetMapping("/{playerId}")
    public ResponseEntity<?> getPlayer(@PathVariable Integer playerId) {
        try {
            return new ResponseEntity<>(playerService.getPlayer(playerId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // search players by firstName route
    @PostMapping("/search")
    public ResponseEntity<?> searchPlayer(@RequestBody SearchRequest term) {
        try {
            System.out.println(playerService.searchPlayers(term));
            return new ResponseEntity<>(playerService.searchPlayers(term), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    private static String UPLOAD_DIR = "uploads";

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
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

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

        }
    }
}
