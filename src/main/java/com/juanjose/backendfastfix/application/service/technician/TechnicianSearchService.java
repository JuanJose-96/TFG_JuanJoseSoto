package com.juanjose.backendfastfix.application.service.technician;

import com.juanjose.backendfastfix.application.PagedResult;
import com.juanjose.backendfastfix.application.port.in.technician.SearchTechniciansUseCase;
import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.domain.model.Technician;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnicianSearchService implements SearchTechniciansUseCase {
    private final TechnicianRepositoryPort technicianRepositoryPort;

    public TechnicianSearchService(TechnicianRepositoryPort technicianRepositoryPort) {
        this.technicianRepositoryPort = technicianRepositoryPort;
    }

    @Override
    public PagedResult<Technician> searchTechnicians(Long sectorId, String province, String city, Double rating, int page) {
        if (page < 0){
            throw new IllegalArgumentException("Page must be 0 or greater");
        }
        return technicianRepositoryPort.searchTechnicians(sectorId,province,city,rating,page);
    }
}
