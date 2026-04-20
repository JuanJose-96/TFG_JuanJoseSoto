package com.juanjose.backendfastfix.infrastructure.adapter.in.rest;

import com.juanjose.backendfastfix.application.port.in.GetSectorsUseCase;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.SectorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {
    private final GetSectorsUseCase getSectorsUseCase;

    public SectorController(GetSectorsUseCase getSectorsUseCase) {
        this.getSectorsUseCase = getSectorsUseCase;
    }

    @GetMapping
    public ResponseEntity<List<SectorResponse>> getAllSectors(){
        List<SectorResponse> sectors = getSectorsUseCase.getAllSectors()
                .stream().map(sector -> new SectorResponse(sector.getId(), sector.getName()))
                .toList();
        return ResponseEntity.ok(sectors);
    }
}
