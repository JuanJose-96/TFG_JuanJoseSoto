package com.juanjose.backendfastfix.infrastructure.adapter.in.rest.controller.technician;

import com.juanjose.backendfastfix.application.port.in.technician.LoginTechnicianUseCase;
import com.juanjose.backendfastfix.application.port.in.technician.RegisterTechnicianUseCase;
import com.juanjose.backendfastfix.domain.model.Technician;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.LoginTechnicianRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.RegisterTechnicianRequest;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.dto.technician.TechnicianResponse;
import com.juanjose.backendfastfix.infrastructure.adapter.in.rest.mapper.TechnicianRestMapper;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/technician/auth")
public class TechnicianAuthController {
    private final RegisterTechnicianUseCase registerTechnicianUseCase;
    private final LoginTechnicianUseCase loginTechnicianUseCase;

    public TechnicianAuthController(RegisterTechnicianUseCase registerTechnicianUseCase, LoginTechnicianUseCase loginTechnicianUseCase) {
        this.registerTechnicianUseCase = registerTechnicianUseCase;
        this.loginTechnicianUseCase = loginTechnicianUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<TechnicianResponse> registerTechnician(@Valid @RequestBody RegisterTechnicianRequest request){
        Technician technicianSaved = registerTechnicianUseCase.register(
                TechnicianRestMapper.technicianRegistrationToDomain(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TechnicianRestMapper.fromDomain(technicianSaved));

    }

    @PostMapping("/login")
    public ResponseEntity<TechnicianResponse> loginTechnician(@Valid @RequestBody LoginTechnicianRequest request){
        Technician technician = loginTechnicianUseCase.login(request.email(), request.password());

        return ResponseEntity
                .ok(TechnicianRestMapper.fromDomain(technician));

    }
}
