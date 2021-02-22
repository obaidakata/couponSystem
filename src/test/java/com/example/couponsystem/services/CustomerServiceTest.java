package com.example.couponsystem.services;

import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest
{
    LoginManager loginManager;
    @Autowired
    CustomerService customerService;
    @Autowired
    AdminService adminService;

    private Logger logger = new Logger();
    private Customer[] customers;
    private Company[] companies;
    private ArrayList<Coupon> coupons;

    public CustomerServiceTest()
    {
        loginManager = LoginManager.getInstance();
        loginManager.setCustomerService(customerService);

    }

    private void initCoupons()
    {
        coupons = new ArrayList<>();
        coupons.add(new Coupon(1,
                adminService.getCompanyByName("KFC").getId(),
                eCategory.Food,
                "5 Nagets",
                "Chicken Naget Description",
                LocalDate.of(2019, Month.MARCH, 1),
                LocalDate.of(2021, Month.MARCH, 20),
                2,
                60.90,
                "KFC_5_Nagets.png"));

        coupons.add(new Coupon(2, adminService.getCompanyByName("KFC").getId(),
                        eCategory.Food,
                        "4 Nagets",
                        "Chicken Naget Description",
                        LocalDate.of(2019, Month.MARCH,20),
                        LocalDate.of(2021, Month.FEBRUARY,5),
                        2,
                        44.90,
                        "KFC_4_Nagets.png"));

        coupons.add(new Coupon(3, adminService.getCompanyByName("KFC").getId(),
                        eCategory.Restaurant,
                        "Family dinner",
                        "Chicken Breast and Naget Description",
                        LocalDate.of(2020, Month.DECEMBER,1),
                        LocalDate.of(2021, Month.FEBRUARY,10),
                        5,
                        80.90,
                        "KFC_4_Family_Dinner.png"));

        coupons.add(new Coupon(4, adminService.getCompanyByName("KFC").getId(),
                        eCategory.Electricity,
                        "test for delete",
                        "Will be deleted",
                        LocalDate.of(2020, Month.DECEMBER,1),
                        LocalDate.of(2021, Month.FEBRUARY,8),
                        2,
                        80.90,
                        "KFC_4_Family_Dinner.png"));
    }

    public void initData()
    {
        customers = new Customer[]{
                new Customer(1, "Elias", "Khoury", "customer1@gmail.com", "customer1"),
                new Customer(2, "Marcel", "Jamawei", "customer2@gmail.com", "customer2"),
                new Customer(3, "Ahmad", "Salama", "customer3@gmail.com", "customer3")
        };

        companies = new Company[]{
                new Company(1, "KFC", "company1@gmail.com", "customer4"),
                new Company( 2, "Vic",  "company2@gmail.com", "customer4"),
                new Company(3, "MCD",  "company3@gmail.com", "customer4")
        };
    }

    @Test
    void purchaseCoupon()
    {
        initData();
        initCoupons();
        for(Customer customer :customers)
        {
            customerService.login(customer.getEmail(), customer.getPassword());
            for(Coupon coupon : coupons)
            {
                customerService.purchaseCoupon(coupon.getId());
            }
        }
    }

    @Test
    void getCustomerCoupons()
    {
        for(Customer customer :customers)
        {
            customerService.login(customer.getEmail(), customer.getPassword());
            logger.log(customerService.getCustomerCoupons());
        }
    }

    @Test
    void testGetCustomerCoupons()
    {
        initData();
        for(Customer customer :customers)
        {
            customerService.login(customer.getEmail(), customer.getPassword());
            logger.log(customerService.getCustomerCoupons(65));
        }
    }

    @Test
    void testGetCustomerCoupons1()
    {
        initData();
        for(Customer customer :customers)
        {
            customerService.login(customer.getEmail(), customer.getPassword());
            logger.log(customerService.getCustomerCoupons(eCategory.Food));
        }
    }

    @Test
    void getCustomerDetails()
    {
        initData();
        for(Customer customer :customers)
        {
            customerService.login(customer.getEmail(), customer.getPassword());
            Customer customerInDB = customerService.getCustomerDetails();
            if(customerInDB != null)
            {
                logger.log(customerInDB.toString());
            }
        }
    }
}