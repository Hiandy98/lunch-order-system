package com.lunch.ops.backend.store.repository;

import com.lunch.ops.backend.store.entity.StoreSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreSessionRepository extends JpaRepository<StoreSession, Integer> {
}
