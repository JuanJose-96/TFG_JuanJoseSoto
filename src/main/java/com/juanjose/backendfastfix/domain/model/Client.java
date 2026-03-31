package com.juanjose.backendfastfix.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    private boolean verified;


    public Client(String name, String surname, String email, String password,
                  String phone, String profileImageUrl, String province,
                  String city, boolean verified) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.profileImageUrl = profileImageUrl;
        this.province = province;
        this.city = city;
        this.verified = verified;
    }
}
