package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller.technician;

import com.juanjose.backendfastfix.application.port.in.technician.GetTechnicianProfileUseCase;
import com.juanjose.backendfastfix.application.port.in.technician.UpdateTechnicianProfileUseCase;
import com.juanjose.backendfastfix.application.port.in.technician.UploadTechnicianImageUseCase;
import com.juanjose.backendfastfix.domain.model.Technician;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.TechnicianResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.UpdateTechnicianProfileRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.TechnicianRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/technician/profile")
public class TechnicianProfileController {
    private final UpdateTechnicianProfileUseCase updateTechnicianProfileUseCase;
    private final UploadTechnicianImageUseCase uploadTechnicianImageUseCase;
    private final GetTechnicianProfileUseCase getTechnicianProfileUseCase;

    public TechnicianProfileController(UpdateTechnicianProfileUseCase updateTechnicianProfileUseCase, UploadTechnicianImageUseCase uploadTechnicianImageUseCase, GetTechnicianProfileUseCase getTechnicianProfileUseCase) {
        this.updateTechnicianProfileUseCase = updateTechnicianProfileUseCase;
        this.uploadTechnicianImageUseCase = uploadTechnicianImageUseCase;
        this.getTechnicianProfileUseCase = getTechnicianProfileUseCase;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicianResponse> updateProfile(@PathVariable Long id,
                                                            @Valid @RequestBody UpdateTechnicianProfileRequest request){
        Technician technicianUpdated = updateTechnicianProfileUseCase.updateProfile(id, TechnicianRestMapper.updateProfileToDomain(request));

        return ResponseEntity.ok(TechnicianRestMapper.fromDomain(technicianUpdated));
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<TechnicianResponse> uploadImage(@PathVariable Long id,
                                                          @RequestParam("technicianImage") MultipartFile file){
        Technician technician = uploadTechnicianImageUseCase.uploadProfileImage(id,file);
        return ResponseEntity.ok(TechnicianRestMapper.fromDomain(technician));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianResponse> getProfile(@PathVariable Long id){
        Technician technician = getTechnicianProfileUseCase.getProfile(id);

        return ResponseEntity.ok(TechnicianRestMapper.fromDomain(technician));

    }
}
