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
@Table(name = "store_session")
public class StoreSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, updatable = false)
    private Store store;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SessionStatus status;

    @Column
    private LocalDateTime endAt;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public static StoreSession create(Store store, LocalDateTime endAt, LocalDateTime currentTime) {
        StoreSession session = new StoreSession();
        session.store = Objects.requireNonNull(store, "目標餐廳不可為空");
        session.status = SessionStatus.OPENING;
        session.extendDeadline(endAt, currentTime);
        return session;
    }

    public void extendDeadline(LocalDateTime newEndAt, LocalDateTime currentTime) {
        if (this.status == SessionStatus.CLOSED) {
            throw new IllegalStateException("已關閉的點餐階段無法直接延長時間");
        }

        if (newEndAt != null && newEndAt.isBefore(currentTime)) {
            throw new IllegalArgumentException("截止時間不能早於當前時間");
        }
        this.endAt = newEndAt;
    }

    public void closeSession() {
        if (this.status == SessionStatus.CLOSED) {
            return;
        }
        this.status = SessionStatus.CLOSED;
    }

    public void reopenSession(LocalDateTime newEndAt, LocalDateTime currentTime) {
        if (this.status != SessionStatus.CLOSED) {
            throw new IllegalStateException("只有已關閉的點餐階段才能重新開啟");
        }

        this.status = SessionStatus.OPENING;
        this.extendDeadline(newEndAt, currentTime);
    }

    public boolean isExpired(LocalDateTime currentTime) {
        return this.status == SessionStatus.OPENING
                && this.endAt != null
                && currentTime.isAfter(this.endAt);
    }
}
