package com.lucas.lparking.controller;

import com.lucas.lparking.DTO.ParkingRequestDTO;
import com.lucas.lparking.domain.Parking;
import com.lucas.lparking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/parking")
public class ParkingController {

    private final ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping
    public ResponseEntity<String> createParking(ParkingRequestDTO parkingDTO) {
        Parking savedParking = parkingService.save(parkingDTO);
        return new ResponseEntity<>("Parking created successfully: " + savedParking.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parking> getParking(@PathVariable("id") Long id) {
        Parking parking = parkingService.findById(id);
        return new ResponseEntity<>(parking, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParking(@PathVariable("id") Long id) {
        parkingService.delete(id);
        return new ResponseEntity<>("Parking deleted successfully", HttpStatus.OK);
    }
}
