package com.example.couponsystem.services;

import com.example.couponsystem.tables.CompanyRepository;
import com.example.couponsystem.tables.CouponRepository;
import com.example.couponsystem.tables.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService
{
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CustomerRepository customerRepository;

    public abstract boolean login(String email, String password);
}
