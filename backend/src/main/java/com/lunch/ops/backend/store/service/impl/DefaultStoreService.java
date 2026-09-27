package com.lunch.ops.backend.store.service.impl;

import com.lunch.ops.backend.common.exception.ConflictError;
import com.lunch.ops.backend.common.exception.NotFoundError;
import com.lunch.ops.backend.store.entity.Store;
import com.lunch.ops.backend.store.repository.StoreRepository;
import com.lunch.ops.backend.store.service.StoreService;
import com.lunch.ops.backend.store.service.model.StoreCreateCommand;
import com.lunch.ops.backend.store.service.model.StoreCreateResult;
import com.lunch.ops.backend.store.service.model.StoreInfoResult;
import com.lunch.ops.backend.store.service.model.StoreUpdateCommand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultStoreService implements StoreService {

    private final StoreRepository storeRepository;

    public DefaultStoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    @Transactional
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

    private Store getStoreEntityById(int id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new NotFoundError(String.format("找不到餐廳, ID: %d", id)));
    }

    @Override
    @Transactional
    public void delete(int id) {
        Store store = getStoreEntityById(id);
        storeRepository.delete(store);
    }

    @Override
    public StoreInfoResult get(int id) {
        Store store = getStoreEntityById(id);
        return StoreInfoResult.from(store);
    }

    @Override
    @Transactional
    public void update(int id, StoreUpdateCommand command) {
        Store store = getStoreEntityById(id);

        store.update(
                command.name(),
                command.url(),
                command.imageUrl(),
                command.description(),
                command.phoneNumber(),
                command.address(),
                command.menu()
        );
    }
}
