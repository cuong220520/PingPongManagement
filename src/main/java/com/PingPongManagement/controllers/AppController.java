package com.PingPongManagement.controllers;
import com.PingPongManagement.dtos.ResponseMessage;
import com.PingPongManagement.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {
    @GetMapping("/")
    public ResponseEntity<?> hello() {
        try {
            System.out.println("hello");
            return new ResponseEntity<>("hello", HttpStatus.OK);
        } catch (AppException e) {
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
