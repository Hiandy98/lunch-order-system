package com.lunch.ops.backend.store.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class StoreContent {

    private String description;
    private String phoneNumber;
    private String address;

    public StoreContent() {
    }

    public StoreContent(String description, String phoneNumber, String address) {
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }
}