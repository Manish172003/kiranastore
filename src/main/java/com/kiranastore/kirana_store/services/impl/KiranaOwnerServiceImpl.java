package com.kiranastore.kirana_store.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiranastore.kirana_store.dtos.KiranaOwnerRequest;
import com.kiranastore.kirana_store.dtos.KiranaOwnerResponse;
import com.kiranastore.kirana_store.entities.KiranaOwner;
import com.kiranastore.kirana_store.exception.ResourceNotFoundException;
import com.kiranastore.kirana_store.repositories.KiranaOwnerRepository;
import com.kiranastore.kirana_store.services.KiranaOwnerService;

@Service
public class KiranaOwnerServiceImpl implements KiranaOwnerService {

    @Autowired
    private KiranaOwnerRepository repository;

    public List<KiranaOwnerResponse> getAllOwners(){
        return repository.findAll()
                .stream()
                .map(this::mapResponse)
                .collect(Collectors.toList());
    }

    public KiranaOwnerResponse getOwner(Long id){
        KiranaOwner owner = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + id));
        return mapResponse(owner);
    }

    public KiranaOwnerResponse registerOwner(KiranaOwnerRequest request){ 
        KiranaOwner owner = mapEntity(request);
        return mapResponse(repository.save(owner));
    }

    public KiranaOwnerResponse updateOwner(Long id, KiranaOwnerRequest request){
        KiranaOwner owner = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + id));
        owner.setName(request.getName());
        owner.setStoreName(request.getStoreName());
        owner.setPhone(request.getPhone());
        owner.setAddress(request.getAddress());
        return mapResponse(repository.save(owner));
    }

    public void deleteOwner(Long id){
        KiranaOwner owner = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found with id " + id));
        repository.delete(owner);
    }

    private KiranaOwnerResponse mapResponse(KiranaOwner owner){
        KiranaOwnerResponse response = new KiranaOwnerResponse();
        response.setId(owner.getId());
        response.setName(owner.getName());
        response.setEmail(owner.getEmail());
        response.setStoreName(owner.getStoreName());
        response.setPhone(owner.getPhone());
        response.setAddress(owner.getAddress());
        return response;
    }

    private KiranaOwner mapEntity(KiranaOwnerRequest request){
        KiranaOwner owner = new KiranaOwner();
        owner.setName(request.getName());
        owner.setEmail(request.getEmail());
        owner.setPassword(request.getPassword());
        owner.setStoreName(request.getStoreName());
        owner.setPhone(request.getPhone());
        owner.setAddress(request.getAddress());
        return owner;
    }
}