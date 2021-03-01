package com.example.couponsystem.controllers;

import com.example.couponsystem.customExceptions.ApiRequestException;
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
import java.util.List;

@RestController
@RequestMapping(path="/admin")
public class AdminController
{
    @Autowired
    private LoginManager loginManager;

    private AdminService adminService;

    @GetMapping(path="/{email}/{password}")
    public ResponseEntity<Boolean> login(
            @PathVariable("email") String email,
            @PathVariable("password") String password)
    {
        adminService = (AdminService) loginManager.login(email, password, eClientType.Administrator);
        Boolean isLogIn = adminService != null;
        return new ResponseEntity<>(isLogIn, HttpStatus.OK) ;
    }

    //    -------------------- company ------------------------------

    @PostMapping("/company/add")
    public ResponseEntity<Company> addCompany(@RequestBody Company companyToAdd)
    {
        if(adminService != null)
        {
            try
            {
                adminService.addCompany(companyToAdd);
                return new ResponseEntity<>(companyToAdd, HttpStatus.CREATED);
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

    @PutMapping("/company/update")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company)
    {
        if(adminService != null)
        {
            try
            {
                adminService.updateCompany(company);
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

    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") int companyId)
    {
        if(adminService != null)
        {
            try
            {
                adminService.deleteCompany(companyId);
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

    @GetMapping("/company/all")
    public ResponseEntity<ArrayList<Company>> getAllCompanies()
    {
        if(adminService != null)
        {
            try
            {
                ArrayList<Company> companies = adminService.getAllCompanies();
                return new ResponseEntity<>(companies, HttpStatus.OK);
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

    @GetMapping("/company/find/{id}")
    public ResponseEntity<Company> getOneCompany(@PathVariable("id") int companyId)
    {
        if(adminService != null)
        {
            try
            {
                Company company = adminService.getOneCompany(companyId);
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

    //    -------------------- customer ------------------------------

    @PostMapping("/customer/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customerToAdd)
    {
        if(adminService != null)
        {
            try
            {
                adminService.addCustomer(customerToAdd);
                return new ResponseEntity<>(customerToAdd, HttpStatus.CREATED);
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

    @PutMapping("/customer/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer)
    {
        if(adminService != null)
        {
            try
            {
                adminService.updateCustomer(customer);
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

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") int customerId)
    {
        if(adminService != null)
        {
            try
            {
                adminService.deleteCustomer(customerId);
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

    @GetMapping("/customer/all")
    public ResponseEntity<ArrayList<Customer>> getAllCustomers()
    {

        if(adminService != null)
        {
            try
            {
                ArrayList<Customer> customers = adminService.getAllCustomers();
                return new ResponseEntity<>(customers, HttpStatus.OK);
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

    @GetMapping("/customer/find/{id}")
    public ResponseEntity<Customer> getOneCustomer(@PathVariable("id") int customerId)
    {
        if(adminService != null)
        {
            try
            {
                Customer customer = adminService.getOneCustomer(customerId);
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
}
