package com.kiranastore.kirana_store.services.impl;

import org.springframework.stereotype.Service;

import com.kiranastore.kirana_store.dtos.KiranaOwnerRequest;
import com.kiranastore.kirana_store.dtos.KiranaOwnerResponse;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.services.KiranaOwnerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KiranaOwnerServiceImpl implements KiranaOwnerService {

    private final KiranaOwnerRepository ownerRepository;

    public KiranaOwnerServiceImpl(KiranaOwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public KiranaOwnerResponse registerOwner(KiranaOwnerRequest request) {
        if (ownerRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists!");
        }
        KiranaOwner owner = mapToEntity(request);
        return mapToResponse(ownerRepository.save(owner));
    }

    @Override
    public KiranaOwnerResponse getOwnerById(Long id) {
        KiranaOwner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + id));
        return mapToResponse(owner);
    }

    @Override
    public List<KiranaOwnerResponse> getAllOwners() {
        return ownerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public KiranaOwnerResponse updateOwner(Long id, KiranaOwnerRequest request) {
        KiranaOwner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + id));

        owner.setName(request.getName());
        owner.setStoreName(request.getStoreName());
        owner.setPhone(request.getPhone());
        owner.setAddress(request.getAddress());
        return mapToResponse(ownerRepository.save(owner));
    }

    @Override
    public void deleteOwner(Long id) {
        KiranaOwner owner = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + id));
        ownerRepository.delete(owner);
    }

    // 🔹 Mapping functions
    private KiranaOwner mapToEntity(KiranaOwnerRequest request) {
        KiranaOwner owner = new KiranaOwner();
        owner.setName(request.getName());
        owner.setEmail(request.getEmail());
        owner.setPassword(request.getPassword()); // Ideally hash password here
        owner.setStoreName(request.getStoreName());
        owner.setPhone(request.getPhone());
        owner.setAddress(request.getAddress());
        return owner;
    }

    private KiranaOwnerResponse mapToResponse(KiranaOwner owner) {
        KiranaOwnerResponse response = new KiranaOwnerResponse();
        response.setId(owner.getId());
        response.setName(owner.getName());
        response.setEmail(owner.getEmail());
        response.setStoreName(owner.getStoreName());
        response.setPhone(owner.getPhone());
        response.setAddress(owner.getAddress());
        return response;
    }
}

