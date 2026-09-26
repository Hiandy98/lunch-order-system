package com.lunch.ops.backend.store.dto;

import com.lunch.ops.backend.store.entity.Menu;
import com.lunch.ops.backend.store.service.model.StoreCreateResult;

import java.util.List;

public record StoreCreateResponse(
        String name,
        String description,
        String phoneNumber,
        List<Menu> menu
) {
    public static StoreCreateResponse from(StoreCreateResult result) {
        return new StoreCreateResponse(
                result.name(),
                result.description(),
                result.phoneNumber(),
                result.menu()
        );
    }
}
