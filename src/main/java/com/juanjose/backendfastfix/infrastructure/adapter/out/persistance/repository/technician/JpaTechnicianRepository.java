package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.technician;

import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.TechnicianEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaTechnicianRepository extends JpaRepository<TechnicianEntity,Long> {
    boolean existsByEmail(String email);
    Optional<TechnicianEntity> findByEmail(String email);

    @Query("SELECT t FROM TechnicianEntity t WHERE " +
            "(:sectorId IS NULL OR t.sectorEntity.id = :sectorId) AND" +
            "(:province IS NULL OR t.province = :province) AND" +
            "(:city IS NULL OR t.city = :city) AND" +
            "(:rating IS NULL OR  t.averageRating >= :rating)")
    List<TechnicianEntity> searchTechnicians(@Param("sectorId") Long sectorId,
                                             @Param("province") String province,
                                             @Param("city") String city,
                                             @Param("rating") Double rating, Pageable pageable);
}
