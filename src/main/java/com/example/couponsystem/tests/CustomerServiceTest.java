package com.example.couponsystem.tests;

import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.CustomerService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

@Component
@Scope("singleton")
public class CustomerServiceTest
{

    LoginManager loginManager;

    private Logger logger = new Logger();
    private Customer[] customers;
    private Company[] companies;
    private ArrayList<Coupon> coupons;

    @Autowired
    public CustomerServiceTest(LoginManager loginManager)
    {
        this.loginManager = loginManager;
    }

    private void initCoupons()
    {
        AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", eClientType.Administrator);
        Company kfc = adminService.getCompanyByName("KFC");
        if(kfc == null)
        {
            return;
        }

        int kfcId = kfc.getId();
        coupons = new ArrayList<>();
        coupons.add(new Coupon(1,
                kfcId,
                eCategory.Food,
                "5 Nagets",
                "Chicken Naget Description",
                LocalDate.of(2019, Month.MARCH, 1),
                LocalDate.of(2021, Month.MARCH, 20),
                2,
                60.90,
                "KFC_5_Nagets.png"));

        coupons.add(new Coupon(
                        2,
                        kfcId,
                        eCategory.Food,
                        "4 Nagets",
                        "Chicken Naget Description",
                        LocalDate.of(2019, Month.MARCH,20),
                        LocalDate.of(2021, Month.FEBRUARY,5),
                        2,
                        44.90,
                        "KFC_4_Nagets.png"));

        coupons.add(new Coupon(
                        3,
                        kfcId,
                        eCategory.Restaurant,
                        "Family dinner",
                        "Chicken Breast and Naget Description",
                        LocalDate.of(2020, Month.DECEMBER,1),
                        LocalDate.of(2021, Month.FEBRUARY,10),
                        5,
                        80.90,
                        "KFC_4_Family_Dinner.png"));

        coupons.add(new Coupon(
                        4,
                        kfcId,
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

    private CustomerService loginCustomer(String email, String password)
    {
        return (CustomerService) loginManager.login(email, password, eClientType.Customer);
    }

    public void purchaseCoupon()
    {
        initData();
        initCoupons();
        for(Customer customer :customers)
        {
            CustomerService customerService = loginCustomer(customer.getEmail(), customer.getPassword());
            if(customerService != null)
            {
                if(coupons != null)
                {
                    for(Coupon coupon : coupons)
                    {
                        customerService.purchaseCoupon(coupon.getId());
                    }
                }
            }
        }
    }

    public void getCustomerCoupons()
    {
        initData();
        for(Customer customer :customers)
        {
            CustomerService customerService = loginCustomer(customer.getEmail(), customer.getPassword());
            if(customerService != null)
            {
                logger.log(customerService.getCustomerCoupons());
            }
        }
    }

    public void testGetCustomerCoupons()
    {
        initData();
        for(Customer customer :customers)
        {
            CustomerService customerService = loginCustomer(customer.getEmail(), customer.getPassword());
            if(customerService != null)
            {
                logger.log(customerService.getCustomerCoupons(65));
            }
        }
    }

    public void testGetCustomerCoupons1()
    {
        initData();
        for(Customer customer :customers)
        {
            CustomerService customerService = loginCustomer(customer.getEmail(), customer.getPassword());
            if(customerService != null)
            {
                logger.log(customerService.getCustomerCoupons(eCategory.Food));
            }
        }
    }

    public void getCustomerDetails()
    {
        initData();
        for(Customer customer :customers)
        {
            CustomerService customerService = loginCustomer(customer.getEmail(), customer.getPassword());
            if(customerService != null)
            {
                Customer customerInDB = customerService.getCustomerDetails();
                if(customerInDB != null)
                {
                    logger.log(customerInDB.toString());
                }
            }
        }
    }
}