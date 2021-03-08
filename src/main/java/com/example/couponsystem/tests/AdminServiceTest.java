package com.example.couponsystem.tests;

import com.example.couponsystem.utiles.Logger;
import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.services.CustomerService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Scope("singleton")
public class AdminServiceTest
{
    LoginManager loginManager;
    AdminService adminService;

    Logger logger = new Logger();
    private Customer[] customers;
    private Company[] companies;

    @Autowired
    public AdminServiceTest(LoginManager loginManager)
    {
        this.loginManager = loginManager;
        adminService = (AdminService) loginManager.login("admin@admin.com", "admin", eClientType.Administrator);
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
                new Company("KFC", "company1@gmail.com", "company1"),
                new Company("Vic",  "company2@gmail.com", "company2"),
                new Company("MCD",  "company3@gmail.com", "company3")
        };
    }

    public void addCompany()
    {
        for(Company company : companies)
        {
            try
            {
                adminService.addCompany(company);
            }
            catch(Exception e)
            {
                logger.log(e.getMessage());
            }
        }

        logger.log(adminService.getAllCompanies());
    }

    public void updateCompany()
    {
        for(Company company : companies)
        {
            CompanyService companyService = (CompanyService) loginManager.login(company.getEmail(),company.getPassword(), eClientType.Company);
            if(companyService != null) {
                Integer companyInDBId = companyService.getCompanyId();
                if (companyInDBId != null) {
                    company.setId(companyInDBId);
                    company.setName(company.getName().toLowerCase(Locale.ROOT));
                    company.setPassword(company.getPassword().toUpperCase(Locale.ROOT));
                    try {
                        adminService.updateCompany(company);
                    } catch (Exception e) {
                        logger.log(e.getMessage());
                    }
                }
            }
        }

        logger.log(adminService.getAllCompanies());
    }

    public void deleteCompany()
    {
        for(Company company : companies)
        {
            CompanyService companyService = (CompanyService) loginManager.login(company.getEmail(),company.getPassword(), eClientType.Company);
            Integer companyInDBId =  companyService.getCompanyId();
            if(companyInDBId != null)
            {
                try
                {
                    adminService.deleteCompany(companyInDBId);
                }
                catch(Exception e)
                {
                    logger.log(e.getMessage());
                }
            }
        }
        logger.log(adminService.getAllCompanies());

    }

    public void getAllCompanies()
    {
        logger.log(adminService.getAllCompanies());
    }

    public void getOneCompany()
    {
        for(Company company : companies)
        {
            CompanyService companyService = (CompanyService) loginManager.login(company.getEmail(),company.getPassword(), eClientType.Company);
            Integer companyInDBId =  companyService.getCompanyId();
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

    public void addCustomer()
    {
        logger.log("Adding Customers Start");
        for(Customer customer : customers)
        {
            try
            {
                adminService.addCustomer(customer);
            }
            catch(Exception e)
            {
                logger.log(e.getMessage());
            }
        }
        logger.log("Adding Customers Done");
        logger.log(adminService.getAllCustomers());
    }

    public void updateCustomer()
    {
        logger.log("updating customers first name to lower case");
        for(Customer customer : customers)
        {
            CustomerService customerService = (CustomerService) loginManager.login(customer.getEmail(),customer.getPassword(), eClientType.Customer);
            Integer customerInDBId = customerService.getCustomerId();
            if(customerInDBId != null)
            {
                customer.setId(customerService.getCustomerId());
                customer.setFirstName(customer.getFirstName().toLowerCase(Locale.ROOT));
                try
                {
                    adminService.updateCustomer(customer);
                }
                catch(Exception e)
                {
                    logger.log(e.getMessage());
                }
            }
        }
        logger.log("Customer update Done");
        logger.log(adminService.getAllCustomers());
    }

    public void deleteCustomer()
    {
        for(Customer customer : customers)
        {
            CustomerService customerService = (CustomerService) loginManager.login(customer.getEmail(),customer.getPassword(), eClientType.Customer);
            Integer customerInDBId = customerService.getCustomerId();
            if(customerInDBId != null)
            {
                try
                {
                    adminService.deleteCustomer(customerInDBId);
                }
                catch(Exception e)
                {
                    logger.log(e.getMessage());
                }
            }
        }
        logger.log(adminService.getAllCustomers());
    }

    public void getAllCustomers()
    {
        logger.log(adminService.getAllCustomers());
    }

    public void getOneCustomer()
    {
        for(Customer customer : customers)
        {
            CustomerService customerService = (CustomerService) loginManager.login(customer.getEmail(),customer.getPassword(), eClientType.Customer);
            Integer customerInDBId = customerService.getCustomerId();
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


    public void getCompanyByName()
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