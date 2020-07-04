package com.PingPongManagement.services;

import com.PingPongManagement.dtos.AuthenticationResponse;
import com.PingPongManagement.dtos.LoginRequest;
import com.PingPongManagement.dtos.RegisterRequest;
import com.PingPongManagement.exceptions.PingPongManagementRequestException;
import com.PingPongManagement.models.AppUser;
import com.PingPongManagement.repositories.AppUserRepository;
import com.PingPongManagement.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        } catch (BadCredentialsException e) {
            throw new PingPongManagementRequestException("Incorrect username or password", e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        String token = jwtUtil.generateToken(userDetails);

        return new AuthenticationResponse(token);
    }

    public User loadUser() {
        User principal =
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return principal;
    }
}
