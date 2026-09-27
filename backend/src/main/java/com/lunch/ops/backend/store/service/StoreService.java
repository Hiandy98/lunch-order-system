package com.lunch.ops.backend.store.service;

import com.lunch.ops.backend.store.service.model.StoreCreateCommand;
import com.lunch.ops.backend.store.service.model.StoreCreateResult;

public interface StoreService {

    StoreCreateResult create(StoreCreateCommand command);

    void delete(int id);
}
