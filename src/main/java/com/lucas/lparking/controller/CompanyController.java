package com.lucas.lparking.controller;

import com.lucas.lparking.DTO.CompanyRequestDTO;
import com.lucas.lparking.domain.Company;
import com.lucas.lparking.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<String> createCompany(CompanyRequestDTO companyDTO) {
        Company savedCompany = companyService.save(companyDTO);
        return new ResponseEntity<>("Company created successfully: " + savedCompany.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable("id") Long id) {
        Company company = companyService.findById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Long id) {
        companyService.delete(id);
        return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
    }
}
