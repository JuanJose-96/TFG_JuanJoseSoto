package com.juanjose.backendfastfix.infrastructure.adapter.out.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.juanjose.backendfastfix.application.port.out.ImageStoragePort;
import com.juanjose.backendfastfix.domain.exception.ImageUploadException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryImageStorageAdapter implements ImageStoragePort {
    private final Cloudinary cloudinary;

    public CloudinaryImageStorageAdapter(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile file) {
        try {
            Map result = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "fastfix/profiles",
                            "resource_type", "image"
                    ));
            return result.get("secure_url").toString();
        } catch (IOException e) {
            throw new ImageUploadException("Error uploading image");
        }
    }
}
