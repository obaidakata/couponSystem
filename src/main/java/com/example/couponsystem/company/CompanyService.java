package com.example.couponsystem.company;

import com.example.couponsystem.category.eCategory;
import com.example.couponsystem.coupon.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
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

    public List<Company> getCompanies()
    {
        return companyRepository.findAll();
    }
}
