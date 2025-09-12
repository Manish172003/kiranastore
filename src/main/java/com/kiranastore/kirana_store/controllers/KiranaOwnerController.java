package com.kiranastore.kirana_store.controllers;

import com.kiranastore.kirana_store.dtos.KiranaOwnerRequest;
import com.kiranastore.kirana_store.dtos.KiranaOwnerResponse;
import com.kiranastore.kirana_store.services.impl.KiranaOwnerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/owners")
public class KiranaOwnerController {

    @Autowired
    private KiranaOwnerServiceImpl kiranaOwnerService;

    @GetMapping("")
    public List<KiranaOwnerResponse> getAllOwners() {
        return kiranaOwnerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public ResponseEntity<KiranaOwnerResponse> getOwnerById(@PathVariable Long id) {
        return ResponseEntity.ok(kiranaOwnerService.getOwner(id));
    }

    @PostMapping("")
    public ResponseEntity<KiranaOwnerResponse> registerOwner(@RequestBody KiranaOwnerRequest request) {
        return ResponseEntity.ok(kiranaOwnerService.registerOwner(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<KiranaOwnerResponse> updateOwner(
            @PathVariable Long id,
            @RequestBody KiranaOwnerRequest request) {
        return ResponseEntity.ok(kiranaOwnerService.updateOwner(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        kiranaOwnerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
    
}