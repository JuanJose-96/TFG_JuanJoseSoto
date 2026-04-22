package com.juanjose.backendfastfix.application.service;

import com.juanjose.backendfastfix.application.port.in.technician.RegisterTechnicianUseCase;
import com.juanjose.backendfastfix.application.port.out.PasswordEncoderPort;
import com.juanjose.backendfastfix.application.port.out.SectorRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.EmailAlreadyExists;
import com.juanjose.backendfastfix.domain.exception.SectorNotFoundException;
import com.juanjose.backendfastfix.domain.model.Technician;
import org.springframework.stereotype.Service;

@Service
public class TechnicianAuthService implements RegisterTechnicianUseCase {

    private final TechnicianRepositoryPort technicianRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final SectorRepositoryPort sectorRepositoryPort;

    public TechnicianAuthService(TechnicianRepositoryPort technicianRepositoryPort, PasswordEncoderPort passwordEncoderPort, SectorRepositoryPort sectorRepositoryPort) {
        this.technicianRepositoryPort = technicianRepositoryPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.sectorRepositoryPort = sectorRepositoryPort;
    }


    @Override
    public Technician register(Technician technician) {
        if(technicianRepositoryPort.existsByEmail(technician.getEmail())){
            throw new EmailAlreadyExists(technician.getEmail());
        }
        if(!sectorRepositoryPort.exitsById(technician.getMainSectorId())) {
            throw new SectorNotFoundException(technician.getMainSectorId());
        }

        Technician technicianToSave = technician.toBuilder()
                .password(passwordEncoderPort.encode(technician.getPassword())).build();

        return technicianRepositoryPort.save(technicianToSave);
    }
}
