package com.juanjose.backendfastfix.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Client {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String profileImageUrl;
    private String province;
    private String city;
    @Builder.Default
    private boolean whatsappAvailable = true;




}
