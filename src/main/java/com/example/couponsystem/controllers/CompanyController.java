package com.example.couponsystem.controllers;

import com.example.couponsystem.customExceptions.ApiRequestException;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.AdminService;
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
    private LoginManager loginManager;

    private CompanyService companyService;


    @GetMapping(path="/{email}/{password}")
    public ResponseEntity<Boolean> login(
            @PathVariable("email") String email,
            @PathVariable("password") String password)
    {
        companyService = (CompanyService) loginManager.login(email, password, eClientType.Company);
        Boolean isLogIn = companyService != null;
        return new ResponseEntity<>(isLogIn, HttpStatus.OK) ;
    }

    @PostMapping("/add")
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon couponToAdd)
    {
        if(companyService != null)
        {
            try
            {
                companyService.addCoupon(couponToAdd);
                return new ResponseEntity<>(couponToAdd, HttpStatus.CREATED);
            }
            catch(Exception e)
            {
                throw new ApiRequestException(e.getMessage());
            }
        }
        else
        {
            throw new ApiRequestException("Must login before");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon couponToUpdate)
    {
        if(companyService != null)
        {
            try
            {
                companyService.updateCoupon(couponToUpdate);
                return new ResponseEntity<>(couponToUpdate, HttpStatus.OK);
            }
            catch(Exception e)
            {
                throw new ApiRequestException(e.getMessage());
            }
        }
        else
        {
            throw new ApiRequestException("Must login before");
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable("id") int couponId)
    {

        if(companyService != null)
        {
            try
            {
                companyService.deleteCoupon(couponId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            catch(Exception e)
            {
                throw new ApiRequestException(e.getMessage());
            }
        }
        else
        {
            throw new ApiRequestException("Must login before");
        }
    }

    @GetMapping(path = "/coupons")
    public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons()
    {
        if(companyService != null)
        {
            try
            {
                ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons();
                return new ResponseEntity<>(companyCoupons, HttpStatus.OK);
            }
            catch(Exception e)
            {
                throw new ApiRequestException(e.getMessage());
            }
        }
        else
        {
            throw new ApiRequestException("Must login before");
        }
    }

    @GetMapping(path = "/coupons/category/{category}")
    public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@PathVariable("category") eCategory category)
    {
        if(companyService != null)
        {
            try
            {
                ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons(category);
                return new ResponseEntity<>(companyCoupons, HttpStatus.OK);
            }
            catch(Exception e)
            {
                throw new ApiRequestException(e.getMessage());
            }
        }
        else
        {
            throw new ApiRequestException("Must login before");
        }
    }

    @GetMapping(path = "/coupons/price/{maxPrice}")
    public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@PathVariable("maxPrice") double maxPrice)
    {
        if(companyService != null)
        {
            try
            {
                ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons(maxPrice);
                return new ResponseEntity<>(companyCoupons, HttpStatus.OK);
            }
            catch(Exception e)
            {
                throw new ApiRequestException(e.getMessage());
            }
        }
        else
        {
            throw new ApiRequestException("Must login before");
        }
    }

    @GetMapping
    public ResponseEntity<Company> getCompanyDetails()
    {

        if(companyService != null)
        {
            try
            {
                Company company = companyService.getCompanyDetails();
                return new ResponseEntity<>(company, HttpStatus.OK);
            }
            catch(Exception e)
            {
                throw new ApiRequestException(e.getMessage());
            }
        }
        else
        {
            throw new ApiRequestException("Must login before");
        }
    }
}
