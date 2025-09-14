package com.kiranastore.kirana_store.services.impl;


import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;

import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.repositories.UserRepository;
import com.kiranastore.kirana_store.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	 private final KiranaOwnerRepository ownerRepository;

	    public UserServiceImpl(KiranaOwnerRepository ownerRepository) {
	        this.ownerRepository = ownerRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        var owner = ownerRepository.findByEmail(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

	        return User.withUsername(owner.getEmail())
	                .password(owner.getPassword())
	                .roles(owner.getUser().getRole().name()) // Role.OWNER
	                .build();
	    }



}