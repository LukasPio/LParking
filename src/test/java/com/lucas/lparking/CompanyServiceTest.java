package com.lucas.lparking;

import com.lucas.lparking.DTO.CompanyRequestDTO;
import com.lucas.lparking.domain.Company;
import com.lucas.lparking.exception.CnpjAlreadyExistsException;
import com.lucas.lparking.exception.NoSavedCompaniesException;
import com.lucas.lparking.exception.ProvidedIdDoesntExistsException;
import com.lucas.lparking.repository.CompanyRepository;
import com.lucas.lparking.service.CompanyService;
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
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    public void setUp() {
        companyRepository.deleteAll();
    }

    @Test
    @DisplayName("Should create new company successfully")
    void createCompanyCase1() {
        CompanyRequestDTO companyDTO = new CompanyRequestDTO(
                "Tech Solutions Ltda.",
                "12.345.678/0001-99",
                "Rua das Inovações, 123",
                "(11) 98765-4321",
                50,
                20
        );

        Company savedCompany = companyService.save(companyDTO);

        Assertions.assertEquals("Tech Solutions Ltda.", savedCompany.getName());
    }

    @Test
    @DisplayName("Shouldn't create new company when CNPJ already exists")
    void createCompanyCase2() {
        CompanyRequestDTO company1 = new CompanyRequestDTO(
                "Tech Solutions Ltda.",
                "12.345.678/0001-99",
                "Rua das Inovações, 123",
                "(11) 98765-4321",
                50,
                20
        );
        CompanyRequestDTO company2 = new CompanyRequestDTO(
                "Alpha Corp.",
                "12.345.678/0001-99",
                "Avenida Principal, 456",
                "(21) 91234-5678",
                100,
                50
        );

        companyService.save(company1);
        Assertions.assertThrows(CnpjAlreadyExistsException.class, () -> companyService.save(company2));
    }
}
