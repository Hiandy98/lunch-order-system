package com.lunch.ops.backend.store.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
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

    @Embedded
    private StoreContent content;

    @CreatedDate
    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    public static Store create(String name, StoreContent content) {
        Store store = new Store();
        store.updateStoreInfo(name);
        store.updateContent(content);
        return store;
    }

    public void updateStoreInfo(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("store content could not be empty");
        }
        this.name = name;
    }

    public void updateContent(StoreContent content) {
        this.content = Objects.requireNonNull(content, "store content could not be empty");
    }
}