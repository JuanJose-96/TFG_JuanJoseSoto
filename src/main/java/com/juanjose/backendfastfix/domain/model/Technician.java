package com.juanjose.backendfastfix.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Technician {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String profileImageUrl;
    private String province;
    private String city;
    private String aboutMe;
    private Sector sector;
    private String priceDescription;
    @Builder.Default
    private boolean emergencyAvailability = false;
    private String scheduleAvailability;
    @Builder.Default
    private boolean whatsappAvailable = true;
    @Builder.Default
    private double averageRating = 0.0;
    @Builder.Default
    private int totalReviews = 0;

}

