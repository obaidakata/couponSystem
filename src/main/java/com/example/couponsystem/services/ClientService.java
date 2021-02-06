package com.example.couponsystem.services;

import com.example.couponsystem.tables.CompanyRepository;
import com.example.couponsystem.tables.CouponRepository;
import com.example.couponsystem.tables.CustomerRepository;

public abstract class ClientService
{
    protected CompanyRepository companyRepository;
    protected CouponRepository couponRepository;
    protected CustomerRepository customerRepository;

    public abstract boolean login(String email, String password);
}
