package com.juanjose.backendfastfix.application.service.technician;

import com.juanjose.backendfastfix.application.port.in.technician.UpdateTechnicianProfileUseCase;
import com.juanjose.backendfastfix.application.port.in.technician.UploadTechnicianImageUseCase;
import com.juanjose.backendfastfix.application.port.out.ImageStoragePort;
import com.juanjose.backendfastfix.application.port.out.TechnicianRepositoryPort;
import com.juanjose.backendfastfix.domain.exception.InvalidFileException;
import com.juanjose.backendfastfix.domain.exception.TechnicianNotFoundException;
import com.juanjose.backendfastfix.domain.model.Client;
import com.juanjose.backendfastfix.domain.model.Technician;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TechnicianProfileService implements UpdateTechnicianProfileUseCase, UploadTechnicianImageUseCase {
    private final TechnicianRepositoryPort technicianRepositoryPort;
    private final ImageStoragePort imageStoragePort;


    public TechnicianProfileService(TechnicianRepositoryPort technicianRepositoryPort, ImageStoragePort imageStoragePort) {
        this.technicianRepositoryPort = technicianRepositoryPort;
        this.imageStoragePort = imageStoragePort;
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
                .sector(technicianFound.getSector())
                .priceDescription(technician.getPriceDescription())
                .emergencyAvailability(technician.isEmergencyAvailability())
                .scheduleAvailability(technician.getScheduleAvailability())
                .whatsappAvailable(technician.isWhatsappAvailable())
                .build();
        return technicianRepositoryPort.save(technicianUpdated);

    }

    @Override
    public Technician uploadProfileImage(Long id, MultipartFile file) {
        if(file == null || file.isEmpty()){
            throw new InvalidFileException("File is required");
        }
        String contentType = file.getContentType();
        if(contentType == null || !contentType.startsWith("image/")){
            throw new InvalidFileException("File must be an image");
        }
        long maxSize = 5* 1024*1024;
        if(file.getSize() > maxSize){
            throw new InvalidFileException("Image must be less than 5MB");
        }

        Technician technician = technicianRepositoryPort.findById(id)
                .orElseThrow(() -> new TechnicianNotFoundException(id));

        String imageUrl = imageStoragePort.uploadImage(file);
        Technician technicianImage = technician.toBuilder().profileImageUrl(imageUrl).build();
        return technicianRepositoryPort.save(technicianImage);
    }
}
