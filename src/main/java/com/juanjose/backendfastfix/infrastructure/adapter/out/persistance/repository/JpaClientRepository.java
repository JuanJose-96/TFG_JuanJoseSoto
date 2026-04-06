package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository;

import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaClientRepository extends JpaRepository<ClientEntity,Long> {
    boolean existsByEmail(String email);
    Optional<ClientEntity> findByEmail(String email);
}
