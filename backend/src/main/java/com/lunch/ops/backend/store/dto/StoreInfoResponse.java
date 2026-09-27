package com.lunch.ops.backend.store.dto;

import com.lunch.ops.backend.store.entity.Menu;
import com.lunch.ops.backend.store.service.model.StoreInfoResult;

import java.util.List;

public record StoreInfoResponse(
        String name,
        String url,
        String imageUrl,
        String description,
        String phoneNumber,
        String address,
        List<Menu> menu
) {
    public static StoreInfoResponse from(StoreInfoResult result) {
        return new StoreInfoResponse(
                result.name(),
                result.url(),
                result.imageUrl(),
                result.description(),
                result.phoneNumber(),
                result.address(),
                result.menu()
        );
    }
}
