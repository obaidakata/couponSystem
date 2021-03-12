package com.example.couponsystem.controllers;

import com.example.couponsystem.Jwt.TokensManager;
import com.example.couponsystem.Jwt.UserNameAndPasswordAuthenticationRequest;
import com.example.couponsystem.customExceptions.TokenExpiredException;
import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/admin")
public class AdminController
{
    @Autowired
    private LoginManager loginManager;

    private AdminService adminService;

    @Autowired
    private TokensManager tokensManager;

    @GetMapping(path="/login/{email}/{password}")
    public ResponseEntity<Boolean> login(
            @PathVariable("email") String email,
            @PathVariable("password") String password)
    {
        adminService = (AdminService) loginManager.login(email, password, eClientType.Administrator);
        String token = tokensManager.generateToken(new UserNameAndPasswordAuthenticationRequest(email, password));
        boolean isLoginSuccessful = adminService != null;
        return new ResponseEntity<>(isLoginSuccessful, tokensManager.getTokenHeader(token), HttpStatus.OK);
    }

    //    -------------------- company ------------------------------

    @PostMapping("/company/add")
    public ResponseEntity<Company> addCompany(@RequestBody Company companyToAdd, @RequestHeader("Authorization") String  token)
    {
        adminService = (AdminService) loginManager.loginWithToken(token, eClientType.Administrator);
        adminService.addCompany(companyToAdd);
        return new ResponseEntity<>(companyToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/company/update")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company, @RequestHeader("Authorization") String  token)
    {
        adminService = (AdminService) loginManager.loginWithToken(token, eClientType.Administrator);
        adminService.updateCompany(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") int companyId, @RequestHeader("Authorization") String  token)
    {
        adminService = (AdminService) loginManager.loginWithToken(token, eClientType.Administrator);
        adminService.deleteCompany(companyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/company/all")
    public ResponseEntity<ArrayList<Company>> getAllCompanies(@RequestHeader("Authorization") String  token)
    {
        adminService = (AdminService) loginManager.loginWithToken(token, eClientType.Administrator);
        ArrayList<Company> companies = adminService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/company/find/{id}")
    public ResponseEntity<Company> getOneCompany(@PathVariable("id") int companyId, @RequestHeader("Authorization") String  token)
    {
        adminService = (AdminService) loginManager.loginWithToken(token, eClientType.Administrator);
        Company company = adminService.getOneCompany(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    //    -------------------- customer ------------------------------

    @PostMapping("/customer/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customerToAdd, @RequestHeader("Authorization") String  token)
    {
        adminService = (AdminService) loginManager.loginWithToken(token, eClientType.Administrator);
        adminService.addCustomer(customerToAdd);
        return new ResponseEntity<>(customerToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/customer/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @RequestHeader("Authorization") String  token)
    {
        adminService = (AdminService) loginManager.loginWithToken(token, eClientType.Administrator);
        adminService.updateCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") int customerId, @RequestHeader("Authorization") String  token)
    {
        adminService = (AdminService) loginManager.loginWithToken(token, eClientType.Administrator);
        adminService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customer/all")
    public ResponseEntity<ArrayList<Customer>> getAllCustomers(@RequestHeader("Authorization") String  token) throws TokenExpiredException {
        adminService = (AdminService) loginManager.loginWithToken(token, eClientType.Administrator);
        ArrayList<Customer> customers = adminService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customer/find/{id}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable("id") int customerId, @RequestHeader("Authorization") String  token) {
        adminService = (AdminService) loginManager.loginWithToken(token, eClientType.Administrator);
        Customer customer = adminService.getOneCustomer(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
