package com.lunch.ops.backend.store.entity;

import java.math.BigDecimal;

public record CustomOption(
        String name, BigDecimal price
) {}
