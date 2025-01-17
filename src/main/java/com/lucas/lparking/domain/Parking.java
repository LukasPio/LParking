package com.lucas.lparking.domain;

import com.lucas.lparking.DTO.ParkingRequestDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "parking")
public class Parking {
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

    public Parking(String name, String cnpj, String address, String phone, Integer carSpots, Integer motorcycleSpots) {
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.phone = phone;
        this.carSpots = carSpots;
        this.motorcycleSpots = motorcycleSpots;
    }

    public Parking(ParkingRequestDTO parkingRequestDTO) {
        this.name = parkingRequestDTO.name();
        this.cnpj = parkingRequestDTO.cnpj();
        this.address = parkingRequestDTO.adress();
        this.phone = parkingRequestDTO.phone();
        this.carSpots = parkingRequestDTO.carSpots();
        this.motorcycleSpots = parkingRequestDTO.motorcycleSpots();
    }

    public Parking() {}

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

    public void update(ParkingRequestDTO newParking) {
        this.name = newParking.name();
        this.cnpj = newParking.cnpj();
        this.address = newParking.adress();
        this.phone = newParking.phone();
        this.carSpots = newParking.carSpots();
        this.motorcycleSpots = newParking.motorcycleSpots();
    }
}

