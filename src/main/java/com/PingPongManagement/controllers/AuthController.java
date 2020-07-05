package com.PingPongManagement.controllers;

import com.PingPongManagement.dtos.*;
import com.PingPongManagement.models.AppUser;
import com.PingPongManagement.repositories.AppUserRepository;
import com.PingPongManagement.services.AuthService;
import com.PingPongManagement.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RefreshTokenService refreshTokenService;

    // load current user route
    @GetMapping("/")
    public ResponseEntity<?> loadUser() {
        try {
            return new ResponseEntity<>(authService.loadUser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseMessage("There is no user"),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    // login route
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        try {
            return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
        } catch (Exception e) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            if (!errors.isEmpty()) {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new ResponseMessage("Invalid Credentials"),
                    HttpStatus.BAD_REQUEST);
        }
    }

    // register route
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest,
                                    BindingResult bindingResult) {
        try {
            Optional<AppUser> optionalUser =
                    appUserRepository.findByUsername(registerRequest.getUsername());

            if (optionalUser != null) {
                return new ResponseEntity<>(new ResponseMessage("Username already exist!"),
                        HttpStatus.OK);
            }

            authService.signup(registerRequest);
            return new ResponseEntity<>(new ResponseMessage("User Registration Successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    // refresh token route
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest, BindingResult bindingResult) {
        try {
            return new ResponseEntity<>(authService.refreshToken(refreshTokenRequest),
                    HttpStatus.OK);
        } catch (Exception e) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }

    // logout route
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest,
                                    BindingResult bindingResult) {
        try {
            refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());

            return new ResponseEntity<>(new ResponseMessage("Logout successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    }
}
