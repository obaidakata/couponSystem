package com.example.couponsystem.dailyJob;


import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.services.CustomerService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.utiles.Singleton;

import java.util.ArrayList;

public class CouponExpirationDailyJob
{
    private AdminService adminService;
    private CompanyService companyService;
    private CustomerService customerService;

    public void setAdminService(AdminService adminService)
    {
        this.adminService = adminService;
    }

    public void setCompanyService(CompanyService companyService)
    {
        this.companyService = companyService;
    }

    public void setCustomerService(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    public CouponExpirationDailyJob getInstance()
    {
        return Singleton.getInstance(CouponExpirationDailyJob.class);
    }


    public void removeAllExpiredCoupons()
    {
//        ArrayList<Company> companies = adminService.getAllCompanies();
//        if(companies != null)
//        {
//            for(Company company : companies)
//            {
//                if(companyService.login(company.getEmail(), company.getPassword()))
//                {
//                    ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons();
//                    if( companyCoupons != null && !companyCoupons.isEmpty())
//                    {
//                        for(Coupon coupon :companyCoupons)
//                        {
//
//                        }
//                    }
//                }
//            }
//        }
    }
}
