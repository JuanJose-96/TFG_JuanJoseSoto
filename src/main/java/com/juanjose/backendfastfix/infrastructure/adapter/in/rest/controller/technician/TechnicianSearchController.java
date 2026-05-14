package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller.technician;

import com.juanjose.backendfastfix.application.PagedResult;
import com.juanjose.backendfastfix.application.port.in.technician.SearchTechniciansUseCase;
import com.juanjose.backendfastfix.domain.model.Technician;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.PagedResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.TechnicianResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.TechnicianRestMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/technicians/search")
public class TechnicianSearchController {
    private final SearchTechniciansUseCase searchTechniciansUseCase;

    public TechnicianSearchController(SearchTechniciansUseCase searchTechniciansUseCase) {
        this.searchTechniciansUseCase = searchTechniciansUseCase;
    }

    @GetMapping
    public ResponseEntity<PagedResponse<TechnicianResponse>> search(@RequestParam(required = false) Long sectorId,
                                                                    @RequestParam(required = false) String province,
                                                                    @RequestParam(required = false) String city,
                                                                    @RequestParam(required = false) Double rating,
                                                                    @RequestParam(defaultValue = "0") int page){

        PagedResult<Technician> result = searchTechniciansUseCase
                .searchTechnicians(sectorId, province, city, rating, page);

        List<TechnicianResponse> technicians = result.getContent()
                .stream().map(TechnicianRestMapper::fromDomain).toList();

        PagedResponse<TechnicianResponse> response = new PagedResponse<>(
                technicians,
                result.getCurrentPage(),
                result.getTotalPages(),
                result.getTotalElements(),
                result.isHasNext()
        );

        return ResponseEntity.ok(response);

    }
}
