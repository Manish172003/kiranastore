package com.kiranastore.kirana_store.services;


import java.util.List;

import com.kiranastore.kirana_store.dtos.KiranaOwnerRequest;
import com.kiranastore.kirana_store.dtos.KiranaOwnerResponse;

public interface KiranaOwnerService {
    KiranaOwnerResponse registerOwner(KiranaOwnerRequest request);
    KiranaOwnerResponse getOwnerById(Long id);
    List<KiranaOwnerResponse> getAllOwners();
    KiranaOwnerResponse updateOwner(Long id, KiranaOwnerRequest request);
    void deleteOwner(Long id);
}
