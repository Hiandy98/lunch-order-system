package com.lunch.ops.backend.store.service.impl;

import com.lunch.ops.backend.common.exception.ConflictError;
import com.lunch.ops.backend.store.entity.Store;
import com.lunch.ops.backend.store.repository.StoreRepository;
import com.lunch.ops.backend.store.service.StoreService;
import com.lunch.ops.backend.store.service.model.StoreCreateCommand;
import com.lunch.ops.backend.store.service.model.StoreCreateResult;
import org.springframework.stereotype.Service;

@Service
public class DefaultStoreService implements StoreService {

    private final StoreRepository storeRepository;

    public DefaultStoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public StoreCreateResult create(StoreCreateCommand command) {

        Store store = Store.create(
                command.name(),
                command.url(),
                command.imageUrl(),
                command.description(),
                command.phoneNumber(),
                command.address(),
                command.menu()
        );

        Store saveStore = storeRepository.save(store);

        return StoreCreateResult.from(saveStore);
    }
}
