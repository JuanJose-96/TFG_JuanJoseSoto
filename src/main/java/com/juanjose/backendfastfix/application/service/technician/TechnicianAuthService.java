package com.juanjose.backendfastfix.application.service.technician;

import com.juanjose.backendfastfix.application.port.in.technician.LoginTechnicianUseCase;
import com.juanjose.backendfastfix.application.port.in.technician.RegisterTechnicianUseCase;
import com.juanjose.backendfastfix.application.port.out.PasswordEncoderPort;
import com.juanjose.backendfastfix.application.port.out.SectorRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.EmailAlreadyExists;
import com.juanjose.backendfastfix.domain.exception.InvalidPasswordException;
import com.juanjose.backendfastfix.domain.exception.SectorNotFoundException;
import com.juanjose.backendfastfix.domain.exception.TechnicianNotFoundException;
import com.juanjose.backendfastfix.domain.model.Sector;
import com.juanjose.backendfastfix.domain.model.Technician;
import org.springframework.stereotype.Service;

@Service
public class TechnicianAuthService implements RegisterTechnicianUseCase, LoginTechnicianUseCase {

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
        Sector sector = sectorRepositoryPort.findById(technician.getSector().getId())
                .orElseThrow(() -> new SectorNotFoundException(technician.getSector().getId()));

        Technician technicianToSave = technician.toBuilder()
                .sector(sector)
                .password(passwordEncoderPort.encode(technician.getPassword())).build();

        return technicianRepositoryPort.save(technicianToSave);
    }

    @Override
    public Technician login(String email, String password) {
        Technician technician = technicianRepositoryPort.findByEmail(email)
                .orElseThrow(() -> new TechnicianNotFoundException(email));

        if(!passwordEncoderPort.matches(password, technician.getPassword())){
            throw new InvalidPasswordException();
        }
        return technician;
    }
}
