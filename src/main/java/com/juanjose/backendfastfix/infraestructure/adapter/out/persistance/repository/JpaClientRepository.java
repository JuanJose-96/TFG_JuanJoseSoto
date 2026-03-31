package com.juanjose.backendfastfix.infraestructure.adapter.out.persistance.repository;

import com.juanjose.backendfastfix.infraestructure.adapter.out.persistance.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaClientRepository extends JpaRepository<ClientEntity,Long> {
    boolean existsByEmail(String email);
}
