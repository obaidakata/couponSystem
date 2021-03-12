package com.example.couponsystem.tests;

import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.utiles.Logger;
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

    @Autowired
    public CustomerServiceTest(LoginManager loginManager)
    {
        this.loginManager = loginManager;
    }


    public void initData()
    {
        customers = new Customer[]{
                new Customer(1, "Elias", "Khoury", "customer1@gmail.com", "customer1"),
                new Customer(2, "Marcel", "Jamawei", "customer2@gmail.com", "customer2"),
                new Customer(3, "Ahmad", "Salama", "customer3@gmail.com", "customer3")
        };

        companies = new Company[]{
                new Company("KFC", "company1@gmail.com", "company1"),
                new Company("Vic", "company2@gmail.com", "company2"),
                new Company("MCD", "company3@gmail.com", "company3")
        };
    }

    private CustomerService loginCustomer(String email, String password)
    {
        return (CustomerService) loginManager.login(email, password, eClientType.Customer);
    }

    public void purchaseCoupon()
    {
        initData();
        for(Customer customer :customers)
        {
            CustomerService customerService = loginCustomer(customer.getEmail(), customer.getPassword());
            if(customerService != null)
            {
                for(Company company: companies) {
                    CompanyService companyService = (CompanyService) loginManager.login(company.getEmail(), company.getPassword(), eClientType.Company);
                    if(companyService != null) {
                        ArrayList<Coupon> companyCoupons = companyService.getCompanyCoupons();
                        if (companyCoupons != null) {
                            for (Coupon coupon : companyCoupons) {
                                try {
                                    customerService.purchaseCoupon(coupon.getId());
                                } catch (Exception e) {
                                    logger.log(e.getMessage());
                                }
                            }
                        }
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
                try
                {
                    Customer customerInDB = customerService.getCustomerDetails();
                    if(customerInDB != null)
                    {
                        logger.log(customerInDB.toString());
                    }
                }
                catch(Exception e)
                {
                    logger.log(e.getMessage());
                }

            }
        }
    }
}