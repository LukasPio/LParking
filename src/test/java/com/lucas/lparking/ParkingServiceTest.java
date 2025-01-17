package com.lucas.lparking;

import com.lucas.lparking.DTO.ParkingRequestDTO;
import com.lucas.lparking.domain.Parking;
import com.lucas.lparking.exception.CnpjAlreadyExistsException;
import com.lucas.lparking.exception.NoSavedParkingsException;
import com.lucas.lparking.exception.ProvidedIdDoesntExistsException;
import com.lucas.lparking.repository.ParkingRepository;
import com.lucas.lparking.repository.ParkingRepository;
import com.lucas.lparking.service.ParkingService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ParkingServiceTest {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private ParkingRepository parkingRepository;

    @BeforeEach
    public void setUp() {
        parkingRepository.deleteAll();
    }

    @Test
    @DisplayName("Should create new parking successfully")
    void createParkingCase1() {
        ParkingRequestDTO parkingDTO = new ParkingRequestDTO(
                "Tech Solutions Ltda.",
                "12.345.678/0001-99",
                "Rua das Inovações, 123",
                "(11) 98765-4321",
                50,
                20
        );

        Parking savedParking = parkingService.save(parkingDTO);

        Assertions.assertEquals("Tech Solutions Ltda.", savedParking.getName());
    }

    @Test
    @DisplayName("Shouldn't create new parking when CNPJ already exists")
    void createParkingCase2() {
        ParkingRequestDTO parking1 = new ParkingRequestDTO(
                "Tech Solutions Ltda.",
                "12.345.678/0001-99",
                "Rua das Inovações, 123",
                "(11) 98765-4321",
                50,
                20
        );
        ParkingRequestDTO parking2 = new ParkingRequestDTO(
                "Alpha Corp.",
                "12.345.678/0001-99",
                "Avenida Principal, 456",
                "(21) 91234-5678",
                100,
                50
        );

        parkingService.save(parking1);
        Assertions.assertThrows(CnpjAlreadyExistsException.class, () -> parkingService.save(parking2));
    }

    @Test
    @DisplayName("Should get a saved parking by id successfully")
    void getParkingCase1() {
        ParkingRequestDTO parkingDTO = new ParkingRequestDTO(
                "Tech Solutions Ltda.",
                "12.543.678/0001-96",
                "Rua das Inovações, 123",
                "(11) 98765-4321",
                50,
                20);
        Parking savedParking = parkingService.save(parkingDTO);

        Parking requestedParking = parkingService.findById(savedParking.getId());
        Assertions.assertEquals(savedParking.getName(), requestedParking.getName());
    }

    @Test
    @DisplayName("Shouldn't get parking when id doesn't exists")
    void getParkingCase2() {
        Assertions.assertThrows(ProvidedIdDoesntExistsException.class, () -> parkingService.findById(59L));
    }

    @Test
    @DisplayName("Should get all parking successfully")
    void getAllParkingCase1() {
        ParkingRequestDTO parkingDTO = new ParkingRequestDTO(
                "Tech Solutions Ltda.",
                "12.543.678/0001-96",
                "Rua das Inovações, 123",
                "(11) 98765-4321",
                50,
                20);
        parkingService.save(parkingDTO);

        List<Parking> parkingList = parkingService.findAll();
        Assertions.assertFalse(parkingList.isEmpty());
    }

    @Test
    @DisplayName("Shouldn't get all parking when there's no saved parking")
    void getAllParkingCase2() {
        Assertions.assertThrows(NoSavedParkingsException.class, () -> parkingService.findAll());
    }

}
