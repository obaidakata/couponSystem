package com.example.couponsystem.controllers;

import com.example.couponsystem.Jwt.TokensManager;
import com.example.couponsystem.Jwt.UserNameAndPasswordAuthenticationRequest;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.CustomerService;
import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/customer")
public class CustomerController
{
    @Autowired
    private LoginManager loginManager;

    private CustomerService customerService;

    @Autowired
    private TokensManager tokensManager;

    @GetMapping(path="/login/{email}/{password}")
    public ResponseEntity<Boolean> login(
            @PathVariable("email") String email,
            @PathVariable("password") String password)
    {
        customerService = (CustomerService) loginManager.login(email, password, eClientType.Customer);
        String token = tokensManager.generateToken(new UserNameAndPasswordAuthenticationRequest(email, password));
        boolean isLoginSuccessful = customerService != null;
        return new ResponseEntity<>(isLoginSuccessful, tokensManager.getTokenHeader(token), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> purchaseCoupon(@RequestBody int couponId, @RequestHeader("Authorization") String  token)
    {
        customerService = (CustomerService) loginManager.loginWithToken(token, eClientType.Customer);
        customerService.purchaseCoupon(couponId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/coupons")
    public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons(@RequestHeader("Authorization") String  token)
    {
        customerService = (CustomerService) loginManager.loginWithToken(token, eClientType.Customer);
        ArrayList<Coupon> customerCoupons = customerService.getCustomerCoupons();
        return new ResponseEntity<>(customerCoupons, HttpStatus.OK);
    }

    @GetMapping(path = "/coupons/category/{category}")
    public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons(@PathVariable("category") eCategory category, @RequestHeader("Authorization") String  token)
    {
        customerService = (CustomerService) loginManager.loginWithToken(token, eClientType.Customer);
        ArrayList<Coupon> customerCoupons = customerService.getCustomerCoupons(category);
        return new ResponseEntity<>(customerCoupons, HttpStatus.OK);
    }

    @GetMapping(path = "/coupons/price/{maxPrice}")
    public ResponseEntity<ArrayList<Coupon>> getCustomerCoupons(@PathVariable("maxPrice") double maxPrice, @RequestHeader("Authorization") String  token)
    {
        customerService = (CustomerService) loginManager.loginWithToken(token, eClientType.Customer);
        ArrayList<Coupon> customerCoupons = customerService.getCustomerCoupons(maxPrice);
        return new ResponseEntity<>(customerCoupons, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Customer> getCustomerDetails(@RequestHeader("Authorization") String  token)
    {
        customerService = (CustomerService) loginManager.loginWithToken(token, eClientType.Customer);
        Customer customer = customerService.getCustomerDetails();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping(path="/AllCompanies")
    public ResponseEntity<ArrayList<Coupon>> getAllCoupons(@RequestHeader("Authorization") String  token)
    {
        customerService = (CustomerService) loginManager.loginWithToken(token, eClientType.Customer);
        ArrayList<Coupon> coupons = customerService.getAllCoupons();
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping(path="/AllCompanies/{category}")
    public ResponseEntity<ArrayList<Coupon>> getAllCouponsByCategory(@PathVariable("category") eCategory category, @RequestHeader("Authorization") String  token)
    {
        customerService = (CustomerService) loginManager.loginWithToken(token, eClientType.Customer);
        ArrayList<Coupon> coupons = customerService.getAllCouponsByCategory(category);
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping(path="/AllCoupons/price/{maxPrice}")
    public ResponseEntity<ArrayList<Coupon>> getAllCouponsByMaxPrice(@PathVariable("maxPrice") double price, @RequestHeader("Authorization") String  token)
    {
        customerService = (CustomerService) loginManager.loginWithToken(token, eClientType.Customer);
        ArrayList<Coupon> coupons = customerService.getAllCouponsByMaxPrice(price);
        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }
}
