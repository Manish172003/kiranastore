package com.kiranastore.kirana_store.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kiranastore.kirana_store.dtos.KiranaOwnerRequest;
import com.kiranastore.kirana_store.dtos.KiranaOwnerResponse;
import com.kiranastore.kirana_store.services.KiranaOwnerService;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class KiranaOwnerController {

    private final KiranaOwnerService ownerService;

    public KiranaOwnerController(KiranaOwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @PostMapping
    public ResponseEntity<KiranaOwnerResponse> registerOwner(@RequestBody KiranaOwnerRequest request) {
        return ResponseEntity.ok(ownerService.registerOwner(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KiranaOwnerResponse> getOwner(@PathVariable Long id) {
        return ResponseEntity.ok(ownerService.getOwnerById(id));
    }

    @GetMapping
    public ResponseEntity<List<KiranaOwnerResponse>> getAllOwners() {
        return ResponseEntity.ok(ownerService.getAllOwners());
    }

    @PutMapping("/{id}")
    public ResponseEntity<KiranaOwnerResponse> updateOwner(
            @PathVariable Long id,
            @RequestBody KiranaOwnerRequest request) {
        return ResponseEntity.ok(ownerService.updateOwner(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        ownerService.deleteOwner(id);
        return ResponseEntity.noContent().build();
    }
}

