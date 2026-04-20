package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.mapper;

import com.juanjose.backendfastfix.domain.model.Sector;
import com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity.SectorEntity;

public class SectorMapper {
    public static Sector toDomain(SectorEntity entity){
        return new Sector(entity.getId(), entity.getName());
    }
}
