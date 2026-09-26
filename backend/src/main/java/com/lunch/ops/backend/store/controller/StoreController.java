package com.lunch.ops.backend.store.controller;

import com.lunch.ops.backend.store.dto.StoreCreateRequest;
import com.lunch.ops.backend.store.dto.StoreCreateResponse;
import com.lunch.ops.backend.store.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("/create")
    public ResponseEntity<StoreCreateResponse> create(@RequestBody StoreCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(StoreCreateResponse.from(storeService.create(request.toCommand())));
    }
}
