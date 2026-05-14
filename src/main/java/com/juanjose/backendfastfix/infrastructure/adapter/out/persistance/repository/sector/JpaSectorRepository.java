package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.repository.sector;

import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.SectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSectorRepository extends JpaRepository<SectorEntity,Long> {
}
