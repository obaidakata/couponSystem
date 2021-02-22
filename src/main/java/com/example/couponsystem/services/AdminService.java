package com.example.couponsystem.services;

import com.example.couponsystem.customExceptions.Logger;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class AdminService extends ClientService
{
    private final String adminEmail="admin@admin.com";
    private final String adminPassword="admin";
    private Logger logger = new Logger();

    public AdminService()
    {
    }

    @Override
    public boolean login(String email, String password)
    {
        boolean isLoginSuccessful = adminEmail.equals(email) && adminPassword.equals(password);
        if(isLoginSuccessful)
        {
            logger.log("Login Successfully -> "+email);
        }
        else
        {
            logger.log("Login failed");
        }

        return isLoginSuccessful;
    }

    public void addCompany(Company companyToAdd)
    {
        if(companyToAdd != null)
        {
            if(companyRepository.existsCompanyByName(companyToAdd.getName()))
            {
                logger.log("Name: " + companyToAdd.getName() + " exist!");
            }
            else if(companyRepository.existsCompanyByEmail(companyToAdd.getEmail()))
            {
                logger.log("Email: " + companyToAdd.getEmail() + " exist!");
            }
            else
            {
                companyRepository.saveAndFlush(companyToAdd);
            }
        }
    }

    public void updateCompany(Company company)
    {
        if(company != null)
        {
            boolean existCompanyWithSameIDAndName = companyRepository.existsCompanyByIdAndName(company.getId(), company.getName());
            if(existCompanyWithSameIDAndName)
            {
                companyRepository.saveAndFlush(company);
            }
            else
            {
                throw new IllegalStateException("Company with " + company.toString() + " does not exists");
            }
        }
    }

    public void deleteCompany(int companyId)
    {
        Company company = companyRepository.findCompanyById(companyId);
        if(company != null)
        {
            ArrayList<Coupon> companyCoupons = company.getCoupons();
            if(companyCoupons != null)
            {
                for(Coupon coupon : companyCoupons)
                {
                    int couponId = coupon.getId();
                    //todo: check if this works
                    customersVsCouponsRepository.deleteByCouponID(couponId);
                    couponRepository.deleteById(couponId);
                }
            }

            companyRepository.deleteById(companyId);
        }
        else
        {
            throw new IllegalStateException("Company with id "+ companyId+ " does not exists");
        }
    }

    public ArrayList<Company> getAllCompanies()
    {
        ArrayList<Company> companies = new ArrayList<>(companyRepository.findAll());
        if(companies.isEmpty())
        {
            logger.log("Didn't find any company");
        }

        return companies;
    }

    public Company getOneCompany(int companyId)
    {
        Company company = companyRepository.findCompanyById(companyId);
        if(company == null)
        {
            logger.log("Didn't find any company with id" + companyId);
        }

        return company;
    }

    public void addCustomer(Customer customer)
    {
        if(customer != null)
        {
            boolean existsCustomerWithTheSameEmail = customerRepository.existsCustomerByEmail(customer.getEmail());
            if(!existsCustomerWithTheSameEmail)
            {
                customerRepository.saveAndFlush(customer);
            }
            else
            {
                logger.log("Exists cutomer with the same email" + customer.getEmail());
            }
        }
    }

    public void updateCustomer(Customer customer)
    {
        boolean existCustomerWithTheSameId = customerRepository.existsById(customer.getId());
        if(existCustomerWithTheSameId)
        {
            customerRepository.saveAndFlush(customer);
        }
        else
        {
            logger.log("Customer with " + customer.toString() + " does not exists");
        }
    }

    public void deleteCustomer(int customerId)
    {
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer != null)
        {
            // TODO: 06/02/2021 Deal with code replication
            for(Coupon coupon : customer.getCoupons())
            {
//                couponRepository.deletePurchaseCouponByCouponID(coupon.getId());
                couponRepository.deleteById(coupon.getId());
            }

            customerRepository.deleteById(customerId);
        }
        else
        {
            throw new IllegalStateException("Customer with id "+ customerId+ " does not exists");
        }
    }

    public ArrayList<Customer> getAllCustomers()
    {
        ArrayList<Customer> customers = new ArrayList<>(customerRepository.findAll());
        if(customers.isEmpty())
        {
            logger.log("Didn't find any customer");
        }

        return customers;
    }

    public Customer getOneCustomer(int customerId)
    {
        Customer customer = customerRepository.findCustomerById(customerId);
        if(customer == null)
        {
            logger.log("Didn't find customer with id " + customerId);
        }

        return customer;
    }

    public Company getCompanyByName(String name)
    {
        return companyRepository.findCompanyByName(name);
    }

}
