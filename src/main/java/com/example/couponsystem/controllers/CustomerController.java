package com.example.couponsystem.controllers;

import com.example.couponsystem.customExceptions.ApiRequestException;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.services.CustomerService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Hashtable;

@RestController
@RequestMapping(path="/customer")
public class CustomerController
{
    @Autowired
    private LoginManager loginManager;

    private CustomerService customerService;

    @GetMapping(path="/{email}/{password}")
    public ResponseEntity<Boolean> login(
            @PathVariable("email") String email,
            @PathVariable("password") String password)
    {
        customerService = (CustomerService) loginManager.login(email, password, eClientType.Customer);
        Boolean isLogIn = customerService != null;
        return new ResponseEntity<>(isLogIn, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> purchaseCoupon(@RequestBody int couponId)
    {
        if(customerService != null)
        {
            try
            {
                customerService.purchaseCoupon(couponId);
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
    public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons()
    {
        if(customerService != null)
        {
            try
            {
                ArrayList<Coupon> customerCoupons = customerService.getCustomerCoupons();
                return new ResponseEntity<>(customerCoupons, HttpStatus.OK);
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
    public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons(@PathVariable("category") eCategory category)
    {
        if(customerService != null)
        {
            try
            {
                ArrayList<Coupon> customerCoupons = customerService.getCustomerCoupons(category);
                return new ResponseEntity<>(customerCoupons, HttpStatus.OK);
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
    public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons(@PathVariable("maxPrice") double maxPrice)
    {
        if(customerService != null)
        {
            try
            {
                ArrayList<Coupon> customerCoupons = customerService.getCustomerCoupons(maxPrice);
                return new ResponseEntity<>(customerCoupons, HttpStatus.OK);
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
    public ResponseEntity<Customer> getCustomerDetails()
    {
        if(customerService != null)
        {
            try
            {
                Customer customer = customerService.getCustomerDetails();
                return new ResponseEntity<>(customer, HttpStatus.OK);
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

    @GetMapping(path="/AllCompanies")
    public ResponseEntity<ArrayList<Coupon>> getAllCoupons()
    {
        if(customerService != null)
        {
            try
            {
                ArrayList<Coupon> coupons = customerService.getAllCoupons();
                return new ResponseEntity<>(coupons, HttpStatus.OK);
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
