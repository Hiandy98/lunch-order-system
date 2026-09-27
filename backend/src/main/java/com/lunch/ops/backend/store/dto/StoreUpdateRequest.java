package com.lunch.ops.backend.store.dto;

import com.lunch.ops.backend.store.entity.Menu;
import com.lunch.ops.backend.store.service.model.StoreUpdateCommand;

import java.util.List;

public record StoreUpdateRequest(
        String name,
        String url,
        String imageUrl,
        String description,
        String phoneNumber,
        String address,
        List<Menu> menu
) {
    public StoreUpdateCommand toCommand() {
        return new StoreUpdateCommand(
                name,
                url,
                imageUrl,
                description,
                phoneNumber,
                address,
                menu
        );
    }
}
