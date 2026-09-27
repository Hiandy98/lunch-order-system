package com.lunch.ops.backend.store.service.model;

import com.lunch.ops.backend.store.entity.Menu;
import com.lunch.ops.backend.store.entity.Store;

import java.util.List;

public record StoreInfoResult(
        String name,
        String url,
        String imageUrl,
        String description,
        String phoneNumber,
        String address,
        List<Menu> menu
) {
    public static StoreInfoResult from(Store store) {
        return new StoreInfoResult(
                store.getName(),
                store.getUrl(),
                store.getImageUrl(),
                store.getDescription(),
                store.getPhoneNumber(),
                store.getAddress(),
                store.getMenu()
        );
    }
}