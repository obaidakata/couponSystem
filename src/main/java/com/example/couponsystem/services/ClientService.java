package com.example.couponsystem.services;

import com.example.couponsystem.tables.CompanyRepository;
import com.example.couponsystem.tables.CouponRepository;
import com.example.couponsystem.tables.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService
{

    protected CompanyRepository companyRepository;
    protected CouponRepository couponRepository;
    protected CustomerRepository customerRepository;

    public abstract boolean login(String email, String password);

    @Autowired
    public final void setCompanyRepository(CompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository;
    }

    @Autowired
    public final void setCouponRepository(CouponRepository couponRepository)
    {
        this.couponRepository = couponRepository;
    }

    @Autowired
    public final void setCustomerRepository(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;
    }
}
