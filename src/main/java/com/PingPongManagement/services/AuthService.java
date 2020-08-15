package com.PingPongManagement.services;

import com.PingPongManagement.dtos.*;
import com.PingPongManagement.exceptions.AppException;
import com.PingPongManagement.models.AppRole;
import com.PingPongManagement.models.AppUser;
import com.PingPongManagement.models.RefreshToken;
import com.PingPongManagement.models.UserRole;
import com.PingPongManagement.repositories.AppRoleRepository;
import com.PingPongManagement.repositories.AppUserRepository;
import com.PingPongManagement.repositories.UserRoleRepository;
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

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppRoleRepository appRoleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RefreshTokenService refreshTokenService;

    public void register(RegisterRequest registerRequest) {
        AppUser appUser = new AppUser();
        appUser.setUsername(registerRequest.getUsername());
        appUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        appUserRepository.save(appUser);

        AppRole appRole = registerRequest.getAppRole();

        System.out.println(appRole.toString());

        UserRole userRole = new UserRole();

        userRole.setAppRole(appRole);
        userRole.setAppUser(appUser);

        userRoleRepository.save(userRole);
    }

    public AuthenticationResponse login(AppUser appUser) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(),
                    appUser.getPassword()));
        } catch (AppException e) {
            throw new AppException("Incorrect username or password", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(appUser.getUsername());

        String accessToken = jwtUtil.generateToken(userDetails);
        String username = jwtUtil.extractUsername(accessToken);
        RefreshToken refreshToken = refreshTokenService.generateRefreshToken();
        Date expiredAt = jwtUtil.extractExpiration(accessToken);

        return new AuthenticationResponse(accessToken, refreshToken.getRefreshToken(), username,
                expiredAt);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());

        UserDetails userDetails =
                userDetailsService.loadUserByUsername(refreshTokenRequest.getUsername());

        String accessToken = jwtUtil.generateToken(userDetails);

        Date expiredAt = jwtUtil.extractExpiration(accessToken);

        return new AuthenticationResponse(accessToken,
                refreshTokenRequest.getRefreshToken(),
                refreshTokenRequest.getUsername(), expiredAt);
    }

    public UserResponse loadUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        AppUser appUser =
                appUserRepository.findByUsername(principal.getUsername()).orElseThrow(() -> new AppException("User not found!"));

        return new UserResponse(appUser.getUsername(), principal.getAuthorities(), appUser.getTeam());
    }
}
