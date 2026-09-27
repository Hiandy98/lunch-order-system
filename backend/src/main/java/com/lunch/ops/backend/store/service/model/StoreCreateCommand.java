package com.lunch.ops.backend.store.service.model;

import com.lunch.ops.backend.store.entity.Menu;

import java.util.List;

public record StoreCreateCommand(
        String name,
        String url,
        String imageUrl,
        String description,
        String phoneNumber,
        String address,
        List<Menu> menu
) {
    public StoreCreateCommand {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("必須要有店家名稱");
        }

        menu = (menu == null) ? List.of() : List.copyOf(menu);
    }
}
