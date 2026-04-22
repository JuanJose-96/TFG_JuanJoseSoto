package com.juanjose.backendfastfix.application.service.client;

import com.juanjose.backendfastfix.application.port.in.client.UpdateClientProfileUseCase;
import com.juanjose.backendfastfix.application.port.in.client.UploadClientImageUseCase;
import com.juanjose.backendfastfix.application.port.out.ClientRepositoryPort;
import com.juanjose.backendfastfix.application.port.out.ImageStoragePort;
import com.juanjose.backendfastfix.domain.exception.ClientNotFoundException;
import com.juanjose.backendfastfix.domain.exception.InvalidFileException;
import com.juanjose.backendfastfix.domain.model.Client;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ClientProfileService implements UpdateClientProfileUseCase, UploadClientImageUseCase {

    private final ClientRepositoryPort clientRepositoryPort;
    private final ImageStoragePort imageStoragePort;

    public ClientProfileService(ClientRepositoryPort clientRepositoryPort, ImageStoragePort imageStoragePort) {
        this.clientRepositoryPort = clientRepositoryPort;
        this.imageStoragePort = imageStoragePort;
    }


    @Override
    public Client updateProfile(Long id, String name, String surname, String phone, String province, String city, boolean whatsappAvailable) {
        Client client = clientRepositoryPort.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        Client clientUpdated = client.toBuilder()
                .name(name)
                .surname(surname)
                .phone(phone)
                .province(province)
                .city(city)
                .whatsappAvailable(whatsappAvailable)
                .build();

        return clientRepositoryPort.save(clientUpdated);
    }


    @Override
    public Client uploadProfileImage(Long id, MultipartFile file) {
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
        Client client = clientRepositoryPort.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));

        String imageUrl = imageStoragePort.uploadImage(file);
        Client clientImage = client.toBuilder().profileImageUrl(imageUrl).build();
        return clientRepositoryPort.save(clientImage);
    }


}
