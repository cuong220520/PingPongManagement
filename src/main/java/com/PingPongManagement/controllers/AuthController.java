package com.PingPongManagement.controllers;

import com.PingPongManagement.dtos.AuthenticationResponse;
import com.PingPongManagement.dtos.LoginRequest;
import com.PingPongManagement.dtos.RegisterRequest;
import com.PingPongManagement.dtos.ResponseMessage;
import com.PingPongManagement.exceptions.PingPongManagementRequestException;
import com.PingPongManagement.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/")
    public ResponseEntity<User> loadUser() {
        User user = authService.loadUser();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody RegisterRequest registerRequest,
                                    BindingResult bindingResult) {
        try {
            authService.signup(registerRequest);

            return new ResponseEntity<>(new ResponseMessage("User Registration Successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

    }
}
