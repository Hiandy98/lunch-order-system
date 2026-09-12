package com.lunch.ops.backend.store.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public record Menu(
        String name,
        BigDecimal price,
        List<CustomOption> customOptions
) {

    @JsonCreator
    public Menu(
            @JsonProperty("name") String name,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("customOptions") List<CustomOption> customOptions
    ) {
        this.name = name;
        this.price = price;
        this.customOptions = List.copyOf(Objects.requireNonNullElse(customOptions, List.of()));
    }

}
