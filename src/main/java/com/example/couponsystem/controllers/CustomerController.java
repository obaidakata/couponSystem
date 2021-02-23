//package com.example.couponsystem.controllers;
//
//import com.example.couponsystem.enums.eCategory;
//import com.example.couponsystem.services.CustomerService;
//import com.example.couponsystem.tables.Coupon;
//import com.example.couponsystem.tables.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping(path="/customer")
//public class CustomerController
//{
//    @Autowired
//    private CustomerService customerService;
//
//    @GetMapping(path="/{email}/{password}")
//    public ResponseEntity<Boolean> login(
//            @PathVariable("email") String email,
//            @PathVariable("password") String password)
//    {
//        return new ResponseEntity<>(customerService.login(email, password), HttpStatus.OK);
//    }
//
//    @PostMapping("/add")
//    public void purchaseCoupon(int couponId)
//    {
//
//    }
//
//    public ArrayList<Coupon> getCustomerCoupons()
//    {
//
//    }
//
//    public ArrayList<Coupon> getCustomerCoupons(eCategory category)
//    {
//
//    }
//
//    public ArrayList<Coupon> getCustomerCoupons(double maxPrice)
//    {
//
//    }
//
//    public Customer getCustomerDetails()
//    {
//
//    }
//
//
//}
