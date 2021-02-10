package com.example.couponsystem.controllers;

import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path="api/v1/company")
public class CompanyController
{
    @Autowired
    private CompanyService companyService;


    @GetMapping(path="{email}/{password}")
    public boolean login(
            @PathVariable("email") String email,
            @PathVariable("password") String password)
    {
        return companyService.login(email, password);
    }

    @PostMapping
    void addCoupon(@RequestBody Coupon couponToAdd)
    {
        companyService.addCoupon(couponToAdd);
    }

    @PutMapping
    void updateCoupon(@RequestBody Coupon couponToUpdate)
    {
        companyService.updateCoupon(couponToUpdate);
    }

    @DeleteMapping(path = "{companyId}")
    void deleteCoupon(@PathVariable("companyId") int couponId)
    {
        companyService.deleteCoupon(couponId);
    }

    @GetMapping(path = "coupons")
    ArrayList<Coupon> getCompanyCoupons()
    {
        return companyService.getCompanyCoupons();
    }

    @GetMapping(path = "coupons/{category}")
    ArrayList<Coupon> getCompanyCoupons(@PathVariable("category") eCategory category)
    {
        return companyService.getCompanyCoupons(category);
    }

    @GetMapping(path = "coupons/{maxPrice}")
    ArrayList<Coupon> getCompanyCoupons(@PathVariable("maxPrice") double maxPrice)
    {
        return companyService.getCompanyCoupons(maxPrice);
    }

    @GetMapping
    Company getCompanyDetails()
    {
        return companyService.getCompanyDetails();
    }
}
