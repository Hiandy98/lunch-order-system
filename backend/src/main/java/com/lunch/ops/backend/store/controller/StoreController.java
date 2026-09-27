package com.lunch.ops.backend.store.controller;

import com.lunch.ops.backend.store.dto.StoreCreateRequest;
import com.lunch.ops.backend.store.dto.StoreCreateResponse;
import com.lunch.ops.backend.store.dto.StoreInfoResponse;
import com.lunch.ops.backend.store.dto.StoreUpdateRequest;
import com.lunch.ops.backend.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<StoreCreateResponse> createStore(@RequestBody StoreCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StoreCreateResponse.from(storeService.create(request.toCommand())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable int id) {
        storeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreInfoResponse> getStoreInfo(@PathVariable int id) {
        return ResponseEntity.ok()
                .body(StoreInfoResponse.from(storeService.get(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStore(@PathVariable int id, @RequestBody StoreUpdateRequest request) {
        storeService.update(id, request.toCommand());
        return ResponseEntity.noContent().build();
    }
}
