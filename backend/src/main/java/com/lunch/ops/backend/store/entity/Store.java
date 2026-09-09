package com.lunch.ops.backend.store.entity;

import jakarta.persistence.*;
import java.util.Date;

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

    @Column(name = "create_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    protected Store() {
    }

    public Store(String name, StoreContent content) {
        this.name = name;
        this.content = content;
    }

    public void updateStoreInfo(String newName) {
        if (newName != null && !newName.trim().isEmpty()) {
            this.name = newName;
        }
    }

    public void updateContent(StoreContent newContent) {
        if (newContent != null) {
            this.content = newContent;
        }
    }

    @PrePersist
    protected void onCreate() {
        this.createAt = new Date();
        this.updateAt = this.createAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = new Date();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StoreContent getContent() {
        return content;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }
}