package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller.technician;

import com.juanjose.backendfastfix.application.port.in.technician.UpdateTechnicianProfileUseCase;
import com.juanjose.backendfastfix.domain.model.Technician;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.TechnicianResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.UpdateTechnicianProfileRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.TechnicianRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technician/profile")
public class TechnicianProfileController {
    private final UpdateTechnicianProfileUseCase updateTechnicianProfileUseCase;

    public TechnicianProfileController(UpdateTechnicianProfileUseCase updateTechnicianProfileUseCase) {
        this.updateTechnicianProfileUseCase = updateTechnicianProfileUseCase;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicianResponse> updateProfile(@PathVariable Long id,
                                                            @Valid @RequestBody UpdateTechnicianProfileRequest request){
        Technician technicianUpdated = updateTechnicianProfileUseCase.updateProfile(id, TechnicianRestMapper.updateProfileToDomain(request));

        return ResponseEntity.ok(TechnicianRestMapper.fromDomain(technicianUpdated));

    }
}
