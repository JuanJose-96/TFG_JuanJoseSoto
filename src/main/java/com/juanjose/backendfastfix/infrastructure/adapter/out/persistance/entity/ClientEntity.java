package com.juanjose.backendfastfix.infrastructure.adapter.out.persistance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String name;
    @Column (nullable = false)
    private String surname;
    @Column (nullable = false, unique = true)
    private String email;
    @Column (nullable = false)
    private String password;
    @Column (nullable = false)
    private String phone;
    private String profileImageUrl;
    @Column (nullable = false)
    private String province;
    @Column (nullable = false)
    private String city;


}
