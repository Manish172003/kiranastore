package com.kiranastore.kirana_store.services.impl;


import org.springframework.stereotype.Service;

import com.kiranastore.kirana_store.dtos.SignupRequest;
import com.kiranastore.kirana_store.dtos.UserDto;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.kiranastore.kirana_store.dtos.SignupRequest;
import com.kiranastore.kirana_store.dtos.AuthenticationResponse;
import com.kiranastore.kirana_store.dtos.KiranaOwnerResponse;
import com.kiranastore.kirana_store.entities.User;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.entities.Role;
import com.kiranastore.kirana_store.repositories.UserRepository;
import com.kiranastore.kirana_store.services.AuthService;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.config.jwtUtil;

@Service
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;
    private final KiranaOwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;
    private final jwtUtil jwtUtil;

    public AuthServiceImplementation(UserRepository userRepository,
                           KiranaOwnerRepository ownerRepository,
                           PasswordEncoder passwordEncoder,
                           jwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.ownerRepository = ownerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public KiranaOwnerResponse registerOwner(SignupRequest request) {
    	if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Save User
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.OWNER);
        User savedUser = userRepository.save(user);

        // Save Owner
        KiranaOwner owner = new KiranaOwner();
        owner.setName(request.getName());
        owner.setEmail(request.getEmail());
        owner.setPassword(savedUser.getPassword()); // if keeping password in owner
        owner.setStoreName(request.getStoreName());
        owner.setPhone(request.getPhone());
        owner.setAddress(request.getAddress());

       
        owner.setUser(savedUser);

        KiranaOwner saved = ownerRepository.save(owner);

        // Generate JWT
        org.springframework.security.core.userdetails.UserDetails userDetails =
                org.springframework.security.core.userdetails.User.withUsername(savedUser.getEmail())
                        .password(savedUser.getPassword())
                        .roles(savedUser.getRole().name())
                        .build();

//        String token = jwtUtil.generateToken(userDetails);

        KiranaOwnerResponse resp = new KiranaOwnerResponse();
        resp.setId(saved.getId());
        resp.setName(request.getName());
        resp.setEmail(request.getEmail());
        resp.setStoreName(request.getStoreName());
        resp.setPhone(request.getPhone());
        resp.setAddress(request.getAddress());
        
        return resp;
    }
}
