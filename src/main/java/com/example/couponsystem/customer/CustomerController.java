package com.example.couponsystem.customer;

import com.example.couponsystem.coupon.Coupon;
import com.example.couponsystem.coupon.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/customer")
public class CustomerController
{
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getCoupons()
    {
        return customerService.getCustomers();
    }
}