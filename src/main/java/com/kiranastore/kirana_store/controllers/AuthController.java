package com.kiranastore.kirana_store.controllers;

import java.io.IOException;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiranastore.kirana_store.config.jwtUtil;
import com.kiranastore.kirana_store.dtos.AuthenticationRequest;
import com.kiranastore.kirana_store.dtos.AuthenticationResponse;
import com.kiranastore.kirana_store.dtos.KiranaOwnerResponse;
import com.kiranastore.kirana_store.dtos.SignupRequest;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.services.AuthService;
import com.kiranastore.kirana_store.services.UserService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.kiranastore.kirana_store.repositories.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final jwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthService authService,
                          AuthenticationManager authenticationManager,
                          com.kiranastore.kirana_store.services.UserService userService,
                          jwtUtil jwtUtil,
                          UserRepository userRepository) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<KiranaOwnerResponse> register(@RequestBody SignupRequest request) {
    	KiranaOwnerResponse resp = authService.registerOwner(request);
        return ResponseEntity.status(201).body(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authReq) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authReq.getEmail(), authReq.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new BadCredentialsException("Invalid credentials");
        }

        // on success, generate token
        org.springframework.security.core.userdetails.UserDetails userDetails =
                userService.loadUserByUsername(authReq.getEmail());

        String token = jwtUtil.generateToken(userDetails);
        com.kiranastore.kirana_store.entities.User u = userRepository.findByEmail(authReq.getEmail()).orElseThrow();

        AuthenticationResponse resp = new AuthenticationResponse();
        resp.setToken(token);
        resp.setUserId(u.getId());
        resp.setUserName(u.getName());

        return ResponseEntity.ok(resp);
    }
}

