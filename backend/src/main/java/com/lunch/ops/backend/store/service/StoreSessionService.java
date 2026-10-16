package com.lunch.ops.backend.store.service;

import com.lunch.ops.backend.store.entity.StoreSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreSessionService extends JpaRepository<StoreSession, Integer> {
}
