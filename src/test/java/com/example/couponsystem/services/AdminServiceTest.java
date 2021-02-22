package com.example.couponsystem.services;

import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Locale;

@SpringBootTest
class AdminServiceTest
{
    LoginManager loginManager;
    @Autowired
    AdminService adminService;

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
                new Company( "KFC", "company1@gmail.com", "customer4"),
                new Company( "Vic",  "company2@gmail.com", "customer4"),
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
        for(Company company : adminService.getAllCompanies())
        {
            company.setName(company.getName().toLowerCase(Locale.ROOT));
            company.setPassword(company.getPassword().toUpperCase(Locale.ROOT));
        }

        logger.log(adminService.getAllCompanies());
    }

    @Test
    void deleteCompany()
    {
        for(Company company : adminService.getAllCompanies())
        {
            adminService.deleteCompany(company.getId());
        }
    }

    @Test
    void getAllCompanies()
    {
    }

    @Test
    void getOneCompany()
    {
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
        for(Customer customer : adminService.getAllCustomers())
        {
            customer.setFirstName(customer.getFirstName().toLowerCase(Locale.ROOT));
            adminService.updateCustomer(customer);
        }
        logger.log("Customer update Done");
        logger.log(adminService.getAllCustomers());
    }

    @Test
    void deleteCustomer()
    {

    }

    @Test
    void getAllCustomers()
    {

    }

    @Test
    void getOneCustomer()
    {

    }

    @Test
    void getCompanyByName()
    {

    }
}