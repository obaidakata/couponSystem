package com.example.couponsystem;

import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.services.CouponService;
import com.example.couponsystem.services.CustomerService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
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
    CouponService couponService;
    @Autowired
    CustomerService customerService;

    Logger logger = new Logger();
    private Customer[] customers;
    private Company[] companies;
    Map<String, Coupon[]> companiesCoupons;

    private void initCoupons()
    {

        companiesCoupons = new HashMap<String, Coupon[]>();

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
        customers = new Customer[]{
                new Customer("Elias", "Khoury", "customer1@gmail.com", "customer1"),
                new Customer("Marcel", "Jamawei", "customer2@gmail.com", "customer2"),
                new Customer("Ahmad", "Salama", "customer3@gmail.com", "customer3")
        };

        companies = new Company[]{
                new Company( "KFC", "company1@gmail.com", "customer4"),
                new Company( "Vic",  "company2@gmail.com", "customer4"),
                new Company("MCD",  "company3@gmail.com", "customer4")
        };
    }

    @Test
    void contextLoads()
    {
        loginManager = LoginManager.getInstance();
        loginManager.setAdminService(adminService);
        loginManager.setCompanyService(companyService);
        loginManager.setCustomerService(customerService);

        testAdminCreation();
        initCoupons();
        testCompany1Start();
        testCompany2Start();
        testCompany3Start();
        testCustomer1Start();
        testCustomer2Start();
        testCustomer3Start();
        testCompanyDeletingCoupon();
        deletingClientsByAdmin();
        TestingExceptions();

    }


    //    Admin Testing
    private void testAdminCreation()
    {
        String email="admin@admin.com";
        String password="admin";
        adminService = (AdminService) loginManager.login(email, password, eClientType.Administrator);

        if(adminService != null)
        {
            //Adding customers
            logger.log("Adding Customers Start");
            for(Customer customer : customers)
            {
                adminService.addCustomer(customer);
            }
            logger.log("Adding Customers Done");
            logger.log(adminService.getAllCustomers());


            //updating customers
            logger.log("updating customers first name to lower case");
            for(Customer customer : adminService.getAllCustomers())
            {
                customer.setFirstName(customer.getFirstName().toLowerCase(Locale.ROOT));
                adminService.updateCustomer(customer);
            }
            logger.log("Customer update Done");
            logger.log(adminService.getAllCustomers());


            //Adding Companies

            logger.log("Adding Companies Start");
            for(Company company : companies)
            {
                adminService.addCompany(company);
            }
            logger.log("Adding Companies Done");
            logger.log(adminService.getAllCompanies());

            //updating Companies
            for(Company company : adminService.getAllCompanies())
            {
                company.setName(company.getName().toLowerCase(Locale.ROOT));
                company.setPassword(company.getPassword().toUpperCase(Locale.ROOT));
            }
            logger.log("Company update Done");
            logger.log(adminService.getAllCustomers());
        }
        else
        {
            logger.log("AdminService is null");
        }
    }

    //    Company Testing
    private void testCompany1Start()
    {
        int test = 1;
        for(Company company : companies)
        {
            logger.log(toString().formatted("Test number %d", test++));
            logger.log(company.getName());
            companyService = (CompanyService) loginManager.login(company.getEmail(),company.getPassword(), eClientType.Company);
            if(companyService != null)
            {
                logger.log("Adding coupons to company -> Start");
                for(Coupon coupon: companiesCoupons.get(company.getName()))
                {
                    companyService.addCoupon(coupon);
                }
                logger.log("Adding Company's Coupons -> End");
                logger.log(companyService.getCompanyCoupons());

                logger.log("updating Company's Coupon");
                ArrayList<Coupon> coupons = companyService.getCompanyCoupons();
                for(Coupon coupon :coupons)
                {

                    coupon.setTitle(coupon.getTitle().toLowerCase(Locale.ROOT));
                    coupon.setAmount(coupon.getAmount() * 10);
                    companyService.updateCoupon(coupon);
                }
                logger.log("updating Company's Coupon -> End");
            }
        }
        logger.log("printing all food coupons");
        logger.log(companyService.getCompanyCoupons(eCategory.Food));
        logger.log("Printing coupons by Max price 50");
        logger.log(companyService.getCompanyCoupons(50));
        logger.log("printing companies details");
        logger.log((List) companyService.getCompanyDetails());

    }

    private void testCompany2Start()
    {
    }

    private void testCompany3Start()
    {
    }

    //    Customer Testing
    private void testCustomer1Start()
    {

    }

    private void testCustomer2Start()
    {

    }

    private void testCustomer3Start()
    {
    }

    //   System Testing
    private void testCompanyDeletingCoupon()
    {
    }

    private void deletingClientsByAdmin()
    {
    }

    private void TestingExceptions()
    {
    }


}
