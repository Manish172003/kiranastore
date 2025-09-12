package com.kiranastore.kirana_store.services;

import java.util.List;

import com.kiranastore.kirana_store.dtos.KiranaOwnerRequest;
import com.kiranastore.kirana_store.dtos.KiranaOwnerResponse;

public interface KiranaOwnerService {
    List<KiranaOwnerResponse> getAllOwners();
    KiranaOwnerResponse getOwner(Long id);
    KiranaOwnerResponse registerOwner(KiranaOwnerRequest owner);
    KiranaOwnerResponse updateOwner(Long id, KiranaOwnerRequest ownerDetails);
    void deleteOwner(Long id);
}