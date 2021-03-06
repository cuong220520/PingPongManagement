package com.PingPongManagement.controllers;

import com.PingPongManagement.dtos.*;
import com.PingPongManagement.exceptions.AppException;
import com.PingPongManagement.models.AppUser;
import com.PingPongManagement.repositories.AppUserRepository;
import com.PingPongManagement.services.AuthService;
import com.PingPongManagement.services.RefreshTokenService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
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
            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // login route
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AppUser appUser,
                                   BindingResult bindingResult) {
        try {
            return new ResponseEntity<>(authService.login(appUser), HttpStatus.OK);
        } catch (Exception e) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            if (!errors.isEmpty()) {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new ResponseMessage("Invalid credentials!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // register route
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest,
                                    BindingResult bindingResult) {
        try {
            Optional<AppUser> optionalUser =
                    appUserRepository.findByUsername(registerRequest.getUsername());

            if (!optionalUser.isEmpty()) {
                return new ResponseEntity<>(new ResponseMessage("Username already exist!"),
                        HttpStatus.OK);
            }

            authService.register(registerRequest);
            return new ResponseEntity<>(new ResponseMessage("User Registration Successfully!"), HttpStatus.OK);
        } catch (Exception e) {
            List<FieldError> errors = bindingResult.getFieldErrors();

            if (!errors.isEmpty()) {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
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

            if (!errors.isEmpty()) {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new ResponseMessage(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
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

            if (!errors.isEmpty()) {
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(new ResponseMessage("Server error!"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
