package com.lunch.ops.backend.store.service;

import com.lunch.ops.backend.store.service.model.SessionActiveCommand;

public interface StoreSessionService {
    void activeStore(SessionActiveCommand command);
}
