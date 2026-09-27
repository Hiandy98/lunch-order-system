package com.lunch.ops.backend.store.service;

import com.lunch.ops.backend.store.service.model.StoreCreateCommand;
import com.lunch.ops.backend.store.service.model.StoreCreateResult;
import com.lunch.ops.backend.store.service.model.StoreInfoResult;
import com.lunch.ops.backend.store.service.model.StoreUpdateCommand;

public interface StoreService {

    StoreCreateResult create(StoreCreateCommand command);

    void delete(int id);

    StoreInfoResult get(int id);

    void update(int id, StoreUpdateCommand command);
}
