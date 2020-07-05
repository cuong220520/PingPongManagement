package com.PingPongManagement.services;

import com.PingPongManagement.dtos.*;
import com.PingPongManagement.exceptions.AppException;
import com.PingPongManagement.models.AppUser;
import com.PingPongManagement.models.RefreshToken;
import com.PingPongManagement.repositories.AppUserRepository;
import com.PingPongManagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public void signup(RegisterRequest registerRequest) {
        AppUser user = new AppUser();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        appUserRepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                    loginRequest.getPassword()));
        } catch (AppException e) {
            throw new AppException("Incorrect username or password", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        String accessToken = jwtUtil.generateToken(userDetails);
        String username = jwtUtil.extractUsername(accessToken);
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken();

        return new AuthenticationResponse(accessToken, refreshToken.getRefreshToken(), username);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(refreshTokenRequest.getUsername());

        String accessToken = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(accessToken,
                refreshTokenRequest.getRefreshToken(),
                refreshTokenRequest.getUsername());
    }

    public UserResponse loadUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new UserResponse(principal.getUsername(), principal.getAuthorities());
    }
}
