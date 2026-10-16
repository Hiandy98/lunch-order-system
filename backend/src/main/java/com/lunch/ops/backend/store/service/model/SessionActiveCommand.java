package com.lunch.ops.backend.store.service.model;

import java.time.LocalDateTime;

public record SessionActiveCommand(
    String storeID,
    LocalDateTime endAt
) {
}
