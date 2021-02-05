package com.example.couponsystem.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService
{
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository;
    }

    public boolean isCompanyExistsByEmailAndPassword(String email, String password)
    {
        return companyRepository.existsCompanyByEmailAndPassword(email, password);
    }

    public void addCompany(Company company)
    {
        companyRepository.saveAndFlush(company);
    }

    public void updateCompany(Company company)
    {
        boolean isCompanyExists = companyRepository.existsById(company.getId());
        if(isCompanyExists)
        {
            companyRepository.saveAndFlush(company);
        }
        else
        {
            throw new IllegalStateException("Company with "+company.toString()+" does not exists");
        }
    }

    public void deleteCompany(int companyId)
    {

        boolean exists = companyRepository.existsById(companyId);
        if(exists)
        {
            companyRepository.deleteById(companyId);
        }
        else
        {
            throw new IllegalStateException("Company with id "+ companyId+ " does not exists");
        }
    }

    public ArrayList<Company> getAllCompanies()
    {
        return new ArrayList<>(companyRepository.findAll()) ;
    }

    public Company getOneCompany(int companyId)
    {
        return companyRepository.findCompanyById(companyId);
    }

//    public int getCompanyIdByCredentials(String email, String password)
//    {
//        boolean exists = companyRepository.existsCompanyByEmailAndPassword(email, password);
//        if(exists)
//        {
//            Company company = companyRepository.findByEmailAndPassword(email, password);
//            return company.getId();
//        }
//        else
//        {
//            throw new IllegalStateException("Company with email "+ email +" and password "+ password+" does not exists");
//        }
//    }
//
//    public boolean isCompanyExistsByEmail(String email)
//    {
//        return companyRepository.existsCompanyByEmail(email);
//    }
}
