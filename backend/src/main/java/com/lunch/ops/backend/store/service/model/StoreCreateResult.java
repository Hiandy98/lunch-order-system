package com.lunch.ops.backend.store.service.model;

import com.lunch.ops.backend.store.entity.Menu;
import com.lunch.ops.backend.store.entity.Store;

import java.util.List;

public record StoreCreateResult(
        String name,
        String description,
        String phoneNumber,
        List<Menu> menu
) {
    public static StoreCreateResult from(Store store) {
        return new StoreCreateResult(
                store.getName(),
                store.getDescription(),
                store.getPhoneNumber(),
                store.getMenu()
        );
    }
}
