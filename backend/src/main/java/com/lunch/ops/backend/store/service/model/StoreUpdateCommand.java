package com.lunch.ops.backend.store.service.model;

import com.lunch.ops.backend.store.entity.Menu;

import java.util.List;

public record StoreUpdateCommand(
        String name,
        String url,
        String imageUrl,
        String description,
        String phoneNumber,
        String address,
        List<Menu> menu
) { }
