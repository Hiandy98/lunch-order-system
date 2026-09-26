package com.lunch.ops.backend.store.dto;

import com.lunch.ops.backend.store.entity.Menu;
import com.lunch.ops.backend.store.service.model.StoreCreateCommand;

import java.util.List;

public record StoreCreateRequest(
        String name,
        String url,
        String imageUrl,
        String description,
        String phoneNumber,
        String address,
        List<Menu> menu
) {
    public StoreCreateCommand toCommand() {
        return new StoreCreateCommand(
                name, url, imageUrl, description, phoneNumber, address, menu
        );
    }
}
