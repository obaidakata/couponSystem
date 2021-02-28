package com.example.couponsystem.controllers;

import com.example.couponsystem.enums.eCategory;
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
    private CustomerService customerService;

    @GetMapping(path="/{email}/{password}")
    public ResponseEntity<Boolean> login(
            @PathVariable("email") String email,
            @PathVariable("password") String password)
    {
        System.out.printf("Login as customer with %s, %s\n", email ,password);
        return new ResponseEntity<>(customerService.login(email, password), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> purchaseCoupon(@RequestBody int couponId)
    {
        customerService.purchaseCoupon(couponId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/coupons")
    public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons()
    {
        ArrayList<Coupon> customerCoupons = customerService.getCustomerCoupons();
        return new ResponseEntity<>(customerCoupons, HttpStatus.OK);
    }

    @GetMapping(path = "/coupons/category/{category}")
    public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons(@PathVariable("category") eCategory category)
    {
        ArrayList<Coupon> customerCoupons = customerService.getCustomerCoupons(category);
        return new ResponseEntity<>(customerCoupons, HttpStatus.OK);
    }

    @GetMapping(path = "/coupons/price/{maxPrice}")
    public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons(@PathVariable("maxPrice") double maxPrice)
    {
        ArrayList<Coupon> customerCoupons = customerService.getCustomerCoupons(maxPrice);
        return new ResponseEntity<>(customerCoupons, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Customer> getCustomerDetails()
    {
        Customer customer = customerService.getCustomerDetails();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(path="/AllCompanies")
    public ResponseEntity<ArrayList<Coupon>> getAllCoupons()
    {
        ArrayList<Coupon> coupons = customerService.getAllCoupons();
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }
}
