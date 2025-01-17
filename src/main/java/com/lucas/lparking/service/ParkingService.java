package com.lucas.lparking.service;

import com.lucas.lparking.DTO.ParkingRequestDTO;
import com.lucas.lparking.domain.Parking;
import com.lucas.lparking.exception.CnpjAlreadyExistsException;
import com.lucas.lparking.exception.NoSavedParkingsException;
import com.lucas.lparking.exception.ProvidedIdDoesntExistsException;
import com.lucas.lparking.repository.ParkingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public Parking save(ParkingRequestDTO parkingDTO) {
        if (parkingRepository.existsByCnpj(parkingDTO.cnpj()))
            throw new CnpjAlreadyExistsException();
        return parkingRepository.save(new Parking(parkingDTO));
    }

    public Parking findById(Long id) {
        Optional<Parking> parking = parkingRepository.findById(id);
        if (parking.isEmpty())
            throw new ProvidedIdDoesntExistsException();
        return parking.get();
    }

    public List<Parking> findAll() {
        List<Parking> companies = parkingRepository.findAll();
        if (companies.isEmpty())
            throw new NoSavedParkingsException();
        return companies;
    }

    @Transactional
    public void delete(Long id) {
        Optional<Parking> parking = parkingRepository.findById(id);
        if (parking.isEmpty())
            throw new ProvidedIdDoesntExistsException();
        parkingRepository.delete(parking.get());
    }


    public void update(Long id, ParkingRequestDTO newParking) {
        Optional<Parking> parkingToUpdate = parkingRepository.findById(id);
        if (parkingToUpdate.isEmpty())
            throw new ProvidedIdDoesntExistsException();
        Parking parking = parkingToUpdate.get();
        parking.update(newParking);
        parkingRepository.save(parking);
    }
}
