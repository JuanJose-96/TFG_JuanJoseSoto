package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.client;

import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.ClientEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface JpaClientRepository extends JpaRepository<ClientEntity,Long> {
    boolean existsByEmail(String email);
    Optional<ClientEntity> findByEmail(String email);
    @Query("SELECT c FROM ClientEntity c WHERE " +
            "(:province IS NULL OR c.province = :province  ) AND " +
            "(:city IS NULL OR c.city = :city) AND" +
            "(:name IS NULL OR LOWER(c.name) LIKE :name)")
    List<ClientEntity> searchClients(@Param("name") String name,
                                     @Param("province") String province,
                                     @Param("city") String city, Pageable pageable);
}
