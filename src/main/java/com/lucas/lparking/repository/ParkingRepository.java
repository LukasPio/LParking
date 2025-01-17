package com.lucas.lparking.repository;

import com.lucas.lparking.domain.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    boolean existsByCnpj(String cnpj);
}
