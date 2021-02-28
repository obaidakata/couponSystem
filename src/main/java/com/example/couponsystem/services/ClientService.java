package com.example.couponsystem.services;

import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.tablesRepo.CompanyRepository;
import com.example.couponsystem.tables.tablesRepo.CouponRepository;
import com.example.couponsystem.tables.tablesRepo.CustomerRepository;
import com.example.couponsystem.tables.tablesRepo.CustomersVsCouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Hashtable;

public abstract class ClientService
{
//    maybe it's better to put @Autowired here
    protected CompanyRepository companyRepository;
    protected CouponRepository couponRepository;
    protected CustomerRepository customerRepository;
    protected CustomersVsCouponsRepository customersVsCouponsRepository;

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

    @Autowired
    public final void setCustomersVsCouponsRepository(CustomersVsCouponsRepository customersVsCouponsRepository)
    {
        this.customersVsCouponsRepository = customersVsCouponsRepository;
    }

}
