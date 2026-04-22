package com.juanjose.backendfastfix.application.service.technician;

import com.juanjose.backendfastfix.application.port.in.technician.UpdateTechnicianProfileUseCase;
import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.TechnicianNotFoundException;
import com.juanjose.backendfastfix.domain.model.Technician;
import org.springframework.stereotype.Service;

@Service
public class TechnicianProfileService implements UpdateTechnicianProfileUseCase {
    private final TechnicianRepositoryPort technicianRepositoryPort;


    public TechnicianProfileService(TechnicianRepositoryPort technicianRepositoryPort) {
        this.technicianRepositoryPort = technicianRepositoryPort;

    }

    @Override
    public Technician updateProfile(Long id, Technician technician) {
        Technician technicianFound = technicianRepositoryPort.findById(id)
                .orElseThrow(() -> new TechnicianNotFoundException(id));
        Technician technicianUpdated = technicianFound.toBuilder()
                .name(technician.getName())
                .surname(technician.getSurname())
                .phone(technician.getPhone())
                .province(technician.getProvince())
                .city(technician.getCity())
                .aboutMe(technician.getAboutMe())
                .mainSectorId(technician.getMainSectorId())
                .priceDescription(technician.getPriceDescription())
                .emergencyAvailability(technician.isEmergencyAvailability())
                .scheduleAvailability(technician.getScheduleAvailability())
                .whatsappAvailable(technician.isWhatsappAvailable())
                .build();
        return technicianRepositoryPort.save(technicianUpdated);

    }
}
