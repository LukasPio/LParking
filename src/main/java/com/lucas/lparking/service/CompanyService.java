package com.lucas.lparking.service;

import com.lucas.lparking.DTO.CompanyRequestDTO;
import com.lucas.lparking.domain.Company;
import com.lucas.lparking.exception.CnpjAlreadyExistsException;
import com.lucas.lparking.exception.NoSavedCompaniesException;
import com.lucas.lparking.exception.ProvidedIdDoesntExistsException;
import com.lucas.lparking.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company save(CompanyRequestDTO companyDTO) {
        if (companyRepository.existsByCnpj(companyDTO.cnpj()))
            throw new CnpjAlreadyExistsException();
        return companyRepository.save(new Company(companyDTO));
    }

    public Company findById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty())
            throw new ProvidedIdDoesntExistsException();
        return company.get();
    }

    public List<Company> findAll() {
        List<Company> companies = companyRepository.findAll();
        if (companies.isEmpty())
            throw new NoSavedCompaniesException();
        return companies;
    }

    @Transactional
    public void delete(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty())
            throw new ProvidedIdDoesntExistsException();
        companyRepository.delete(company.get());
    }


    public void update(Long id, CompanyRequestDTO newCompany) {
        Optional<Company> companyToUpdate = companyRepository.findById(id);
        if (companyToUpdate.isEmpty())
            throw new ProvidedIdDoesntExistsException();
        Company company = companyToUpdate.get();
        company.update(newCompany);
        companyRepository.save(company);
    }
}
