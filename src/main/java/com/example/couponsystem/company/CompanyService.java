package com.example.couponsystem.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService
{
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies()
    {
        return companyRepository.findAll();
    }

}
