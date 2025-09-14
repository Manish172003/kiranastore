package com.kiranastore.kirana_store.services;

import com.kiranastore.kirana_store.dtos.AuthenticationResponse;
import com.kiranastore.kirana_store.dtos.KiranaOwnerResponse;
import com.kiranastore.kirana_store.dtos.SignupRequest;



public interface AuthService {
    // register owner and return AuthenticationResponse containing user id/name/token if desired
	KiranaOwnerResponse registerOwner(SignupRequest request);
}
