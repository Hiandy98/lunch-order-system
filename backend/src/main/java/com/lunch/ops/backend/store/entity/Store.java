package com.lunch.ops.backend.store.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String url;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(length = 255)
    private String address;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "menu", columnDefinition = "jsonb")
    private List<Menu> menu = new ArrayList<>();

    @CreatedDate
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    public static Store create(
            String name, String url, String imageUrl, String description, String phoneNumber, String address,
            List<Menu> menu
    ) {
        Store store = new Store();
        store.changeName(name);
        store.updateDetails(url, imageUrl, description, phoneNumber, address);
        store.updateMenu(menu);
        return store;
    }

    public void update(
            String name, String url, String imageUrl, String description, String phoneNumber, String address,
            List<Menu> menu
    ) {
        this.changeName(name);
        this.updateDetails(url, imageUrl, description, phoneNumber, address);
        this.updateMenu(menu);
    }

    public void changeName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("store name could not be empty");
        }
        this.name = name;
    }

    public void updateDetails(String url, String imageUrl, String description, String phoneNumber, String address) {
        this.url = url;
        this.imageUrl = imageUrl;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void updateMenu(List<Menu> menu) {
        List<Menu> validatedMenu = List.copyOf(Objects.requireNonNullElse(menu, List.of()));

        if (this.menu == null) {
            this.menu = new ArrayList<>();
        }
        this.menu.clear();
        this.menu.addAll(validatedMenu);
    }
}