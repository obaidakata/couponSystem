package com.example.couponsystem.controllers;

import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/admin")
public class AdminController
{
    @Autowired
    private AdminService adminService;

    @GetMapping(path="/{email}/{password}")
    public ResponseEntity<Boolean> login(
            @PathVariable("email") String email,
            @PathVariable("password") String password)
    {
        return new ResponseEntity<>(adminService.login(email, password), HttpStatus.OK) ;
    }

    //    -------------------- company ------------------------------

    @PostMapping("/company/add")
    public ResponseEntity<Company> addCompany(@RequestBody Company companyToAdd)
    {
        adminService.addCompany(companyToAdd);
        return new ResponseEntity<>(companyToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/company/update")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company)
    {
        adminService.updateCompany(company);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") int companyId)
    {
        adminService.deleteCompany(companyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/company/all")
    public ResponseEntity<ArrayList<Company>> getAllCompanies()
    {
        adminService.login("admin@admin.com", "admin");
        ArrayList<Company> companies = adminService.getAllCompanies();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/company/find/{id}")
    public ResponseEntity<Company> getOneCompany(@PathVariable("id") int companyId)
    {
        Company company = adminService.getOneCompany(companyId);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    //    -------------------- customer ------------------------------

    @PostMapping("/customer/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customerToAdd)
    {
        System.out.println("Created");
        adminService.addCustomer(customerToAdd);
        return new ResponseEntity<>(customerToAdd, HttpStatus.CREATED);
    }

    @PutMapping("/customer/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer)
    {
        adminService.updateCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") int customerId)
    {
        adminService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/customer/all")
    public ResponseEntity<ArrayList<Customer>> getAllCustomers()
    {
        ArrayList<Customer> customers = adminService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/customer/find/{id}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable("id") int customerId)
    {
        Customer customer = adminService.getOneCustomer(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


}
