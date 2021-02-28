package com.example.couponsystem.controllers;

import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.spi.CollatorProvider;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="/company")
public class CompanyController
{
    @Autowired
    private CompanyService companyService;


    @GetMapping(path="/{email}/{password}")
    public ResponseEntity<Boolean> login(
            @PathVariable("email") String email,
            @PathVariable("password") String password)
    {
        System.out.printf("Login as company with %s, %s\n", email ,password);
        return new ResponseEntity<>(companyService.login(email, password), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon couponToAdd)
    {
        companyService.addCoupon(couponToAdd);
        return new ResponseEntity<>(couponToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon couponToUpdate)
    {
        companyService.updateCoupon(couponToUpdate);
        return new ResponseEntity<>(couponToUpdate, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable("id") int couponId)
    {
        companyService.deleteCoupon(couponId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/coupons")
    public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons()
    {
        ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons();
        return new ResponseEntity<>(companyCoupons, HttpStatus.OK);
    }

    @GetMapping(path = "/coupons/category/{category}")
    public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@PathVariable("category") eCategory category)
    {
        ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons(category);
        return new ResponseEntity<>(companyCoupons, HttpStatus.OK);
    }

    @GetMapping(path = "/coupons/price/{maxPrice}")
    public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@PathVariable("maxPrice") double maxPrice)
    {
        ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons(maxPrice);
        return new ResponseEntity<>(companyCoupons, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Company> getCompanyDetails()
    {
        Company company = companyService.getCompanyDetails();
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
