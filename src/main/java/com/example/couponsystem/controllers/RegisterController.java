package com.example.couponsystem.controllers;

import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.RegisterService;
import com.example.couponsystem.tables.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customerToAdd)
    {
        registerService.addCustomer(customerToAdd);
        return new ResponseEntity<>(customerToAdd, HttpStatus.CREATED);
    }
}
