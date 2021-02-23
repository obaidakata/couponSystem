package com.example.couponsystem.services;

import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Locale;

@SpringBootTest
class AdminServiceTest
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

    public AdminServiceTest()
    {
        loginManager = LoginManager.getInstance();
        loginManager.setAdminService(adminService);
        initData();
    }


    public void initData()
    {
        customers = new Customer[]{
                new Customer("Elias", "Khoury", "customer1@gmail.com", "customer1"),
                new Customer("Marcel", "Jamawei", "customer2@gmail.com", "customer2"),
                new Customer("Ahmad", "Salama", "customer3@gmail.com", "customer3")
        };

        companies = new Company[]{
                new Company("KFC", "company1@gmail.com", "customer4"),
                new Company("Vic",  "company2@gmail.com", "customer4"),
                new Company("MCD",  "company3@gmail.com", "customer4")
        };
    }

    @Test
    void addCompany()
    {
        for(Company company : companies)
        {
            adminService.addCompany(company);
        }

        logger.log(adminService.getAllCompanies());
    }

    @Test
    void updateCompany()
    {
        for(Company company : companies)
        {
            Integer companyInDBId =  companyService.getCompanyId(company.getEmail(), company.getPassword());
            if(companyInDBId != null)
            {
                company.setId(companyInDBId);
                company.setName(company.getName().toLowerCase(Locale.ROOT));
                company.setPassword(company.getPassword().toUpperCase(Locale.ROOT));
                adminService.updateCompany(company);
            }
        }

        logger.log(adminService.getAllCompanies());
    }

    @Test
    void deleteCompany()
    {
        for(Company company : companies)
        {
            Integer companyInDBId =  companyService.getCompanyId(company.getEmail(), company.getPassword());
            if(companyInDBId != null)
            {
                adminService.deleteCompany(companyInDBId);
            }
        }
        logger.log(adminService.getAllCompanies());
    }

    @Test
    void getAllCompanies()
    {
        logger.log(adminService.getAllCompanies());
    }

    @Test
    void getOneCompany()
    {
        for(Company company : companies)
        {
            Integer companyInDBId =  companyService.getCompanyId(company.getEmail(), company.getPassword());
            if(companyInDBId != null)
            {
                Company companyFromDB = adminService.getOneCompany(companyInDBId);
                if(companyFromDB != null)
                {
                    logger.log(companyFromDB.toString());
                }
            }
        }
    }

    @Test
    void addCustomer()
    {
        logger.log("Adding Customers Start");
        for(Customer customer : customers)
        {
            adminService.addCustomer(customer);
        }
        logger.log("Adding Customers Done");
        logger.log(adminService.getAllCustomers());
    }

    @Test
    void updateCustomer()
    {
        logger.log("updating customers first name to lower case");
        for(Customer customer : customers)
        {
            Integer customerInDBId = customerService.getCustomerId(customer.getEmail(),customer.getPassword());
            if(customerInDBId != null)
            {
                customer.setId(customerService.getCustomerId(customer.getEmail(), customer.getPassword()));
                customer.setFirstName(customer.getFirstName().toLowerCase(Locale.ROOT));
                adminService.updateCustomer(customer);
            }
        }
        logger.log("Customer update Done");
        logger.log(adminService.getAllCustomers());
    }

    @Test
    void deleteCustomer()
    {
        for(Customer customer : customers)
        {
            Integer customerInDBId = customerService.getCustomerId(customer.getEmail(), customer.getPassword());
            if(customerInDBId != null)
            {
                adminService.deleteCustomer(customerInDBId);
            }
        }
        logger.log(adminService.getAllCustomers());
    }

    @Test
    void getAllCustomers()
    {
        logger.log(adminService.getAllCustomers());
    }

    @Test
    void getOneCustomer()
    {
        for(Customer customer : customers)
        {
            Integer customerInDBId = customerService.getCustomerId(customer.getEmail(), customer.getPassword());
            if(customerInDBId != null)
            {
                Customer customerInDB = adminService.getOneCustomer(customerInDBId);
                if(customerInDB != null)
                {
                    logger.log(customerInDB.toString());
                }
            }
        }
    }

    @Test
    void getCompanyByName()
    {
        for(Company company :companies)
        {
            Company companyInDB = adminService.getCompanyByName(company.getName());
            if(companyInDB != null)
            {
                logger.log(companyInDB.toString());
            }
        }
    }
}