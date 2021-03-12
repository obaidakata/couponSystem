package com.example.couponsystem.controllers;

import com.example.couponsystem.Jwt.TokensManager;
import com.example.couponsystem.Jwt.UserNameAndPasswordAuthenticationRequest;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/company")
public class CompanyController
{
    @Autowired
    private LoginManager loginManager;

    @Autowired
    private TokensManager tokensManager;

    private CompanyService companyService;

    @GetMapping(path="/login/{email}/{password}")
    public ResponseEntity<Boolean> login(
            @PathVariable("email") String email,
            @PathVariable("password") String password)
    {
        companyService = (CompanyService) loginManager.login(email, password, eClientType.Company);
        String token = tokensManager.generateToken(new UserNameAndPasswordAuthenticationRequest(email, password));
        boolean isLoginSuccessful = companyService != null;
        return new ResponseEntity<>(isLoginSuccessful, tokensManager.getTokenHeader(token), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon couponToAdd, @RequestHeader("Authorization") String  token){
        companyService = (CompanyService) loginManager.loginWithToken(token, eClientType.Company);
        companyService.addCoupon(couponToAdd);
        return new ResponseEntity<>(couponToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Coupon> updateCoupon(@RequestBody Coupon couponToUpdate, @RequestHeader("Authorization") String  token)
    {
        companyService = (CompanyService) loginManager.loginWithToken(token, eClientType.Company);
        companyService.updateCoupon(couponToUpdate);
        return new ResponseEntity<>(couponToUpdate, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable("id") int couponId, @RequestHeader("Authorization") String  token)
    {
        companyService = (CompanyService) loginManager.loginWithToken(token, eClientType.Company);
        companyService.deleteCoupon(couponId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/coupons")
    public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@RequestHeader("Authorization") String  token)
    {
        companyService = (CompanyService) loginManager.loginWithToken(token, eClientType.Company);
        ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons();
        return new ResponseEntity<>(companyCoupons, HttpStatus.OK);
    }

    @GetMapping(path = "/coupons/category/{category}")
    public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@PathVariable("category") eCategory category, @RequestHeader("Authorization") String  token)
    {
        companyService = (CompanyService) loginManager.loginWithToken(token, eClientType.Company);
        ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons(category);
        return new ResponseEntity<>(companyCoupons, HttpStatus.OK);
    }

    @GetMapping(path = "/coupons/price/{maxPrice}")
    public ResponseEntity<ArrayList<Coupon>> getCompanyCoupons(@PathVariable("maxPrice") double maxPrice, @RequestHeader("Authorization") String  token)
    {
        companyService = (CompanyService) loginManager.loginWithToken(token, eClientType.Company);
        ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons(maxPrice);
        return new ResponseEntity<>(companyCoupons, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Company> getCompanyDetails(@RequestHeader("Authorization") String  token)
    {
        companyService = (CompanyService) loginManager.loginWithToken(token, eClientType.Company);
        Company company = companyService.getCompanyDetails();
        return new ResponseEntity<>(company, HttpStatus.OK);
    }
}
