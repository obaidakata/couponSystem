package com.example.couponsystem;

import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.services.CustomerService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@SpringBootTest
class CouponsystemApplicationTests
{
    LoginManager loginManager;
    @Autowired
    AdminService adminService;
    @Autowired
    CompanyService companyService;
    @Autowired
    CustomerService customerService;

    Logger logger = new Logger();
    private Customer[] customers;
    private Company[] companies;
    Map<String, Coupon[]> companiesCoupons;

    private void initCoupons()
    {

        companiesCoupons = new HashMap<>();

        companiesCoupons.put(
                "KFC",
                new Coupon[]{
                        new Coupon(
                                adminService.getCompanyByName("KFC").getId(),
                                eCategory.Food,
                                "5 Nagets",
                                "Chicken Naget Description",
                                LocalDate.of(2019, Month.MARCH, 1),
                                LocalDate.of(2021, Month.MARCH, 20),
                                2,
                                60.90,
                                "KFC_5_Nagets.png"),

                        new Coupon(adminService.getCompanyByName("KFC").getId(),
                                eCategory.Food,
                                "4 Nagets",
                                "Chicken Naget Description",
                                LocalDate.of(2019, Month.MARCH,20),
                                LocalDate.of(2021, Month.FEBRUARY,5),
                                2,
                                44.90,
                                "KFC_4_Nagets.png"),

                        new Coupon(adminService.getCompanyByName("KFC").getId(),
                                eCategory.Restaurant,
                                "Family dinner",
                                "Chicken Breast and Naget Description",
                                LocalDate.of(2020, Month.DECEMBER,1),
                                LocalDate.of(2021, Month.FEBRUARY,10),
                                5,
                                80.90,
                                "KFC_4_Family_Dinner.png"),

                        new Coupon(adminService.getCompanyByName("KFC").getId(),
                                eCategory.Electricity,
                                "test for delete",
                                "Will be deleted",
                                LocalDate.of(2020, Month.DECEMBER,1),
                                LocalDate.of(2021, Month.FEBRUARY,8),
                                2,
                                80.90,
                                "KFC_4_Family_Dinner.png")
                });
    }

    public CouponsystemApplicationTests()
    {
    }

    @Test
    void contextLoads()
    {
    }


}
