package com.lunch.ops.backend.store.service.impl;

import com.lunch.ops.backend.store.repository.StoreRepository;
import com.lunch.ops.backend.store.service.StoreService;
import com.lunch.ops.backend.store.service.model.StoreCreateCommand;
import com.lunch.ops.backend.store.service.model.StoreCreateResult;

public class DefaultStoreService implements StoreService {

    private final StoreRepository storeRepository;

    public DefaultStoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreCreateResult create(StoreCreateCommand command) {
        return null;
    }
}
