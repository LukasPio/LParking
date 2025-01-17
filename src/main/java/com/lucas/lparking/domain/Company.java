package com.lucas.lparking.domain;

import com.lucas.lparking.DTO.CompanyRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cnpj;
    private String address;
    private String phone;
    @Column(name = "car_spots")
    private Integer carSpots;
    @Column(name = "motorcycle_spots")
    private Integer motorcycleSpots;

    public Company(String name, String cnpj, String address, String phone, Integer carSpots, Integer motorcycleSpots) {
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.phone = phone;
        this.carSpots = carSpots;
        this.motorcycleSpots = motorcycleSpots;
    }

    public Company(CompanyRequestDTO companyRequestDTO) {
        this.name = companyRequestDTO.name();
        this.cnpj = companyRequestDTO.cnpj();
        this.address = companyRequestDTO.adress();
        this.phone = companyRequestDTO.phone();
        this.carSpots = companyRequestDTO.carSpots();
        this.motorcycleSpots = companyRequestDTO.motorcycleSpots();
    }

    public Company() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getCarSpots() {
        return carSpots;
    }

    public Integer getMotorcycleSpots() {
        return motorcycleSpots;
    }

    public void update(CompanyRequestDTO newCompany) {
        this.name = newCompany.name();
        this.cnpj = newCompany.cnpj();
        this.address = newCompany.adress();
        this.phone = newCompany.phone();
        this.carSpots = newCompany.carSpots();
        this.motorcycleSpots = newCompany.motorcycleSpots();
    }
}

