package com.example.couponsystem.services;

import com.example.couponsystem.customExceptions.ServiceException;
import com.example.couponsystem.utiles.Logger;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class AdminService extends ClientService
{
    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

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
                throw new ServiceException("Name: " + companyToAdd.getName() + " exist!");
            }
            else if(companyRepository.existsCompanyByEmail(companyToAdd.getEmail()))
            {
                throw new ServiceException("Email: " + companyToAdd.getEmail() + " exist!");
            }
            else
            {
                companyRepository.saveAndFlush(companyToAdd);
            }
        }
    }

    @Transactional
    public void updateCompany(Company companyToUpdate)
    {
        if(companyToUpdate != null)
        {
            if(companyRepository.existsCompanyByIdAndName(companyToUpdate.getId(), companyToUpdate.getName()))
            {
                companyRepository.saveAndFlush(companyToUpdate);
            }
            else
            {
                throw new ServiceException("Company does not exists");
            }
        }
    }

    @Transactional
    public void deleteCompany(int companyId)
    {
        if(companyRepository.existsCompanyById(companyId))
        {
            ArrayList<Coupon> companyCoupons =  couponRepository.getCouponsByCompanyID(companyId);
            if(companyCoupons != null)
            {
                for(Coupon coupon : companyCoupons)
                {
                    int couponId = coupon.getId();
                    customersVsCouponsRepository.deleteCustomersVsCouponsByCouponID(couponId);
                    couponRepository.deleteById(couponId);
                }
            }

            companyRepository.deleteById(companyId);
        }
        else
        {
            throw new ServiceException("Company with id "+ companyId+ " does not exists");
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
                throw new ServiceException("Exists customer with the same email" + customer.getEmail());
            }
        }
    }

    @Transactional
    public void updateCustomer(Customer customerToUpdate)
    {
        if(customerToUpdate != null) {
            boolean existCustomerWithTheSameId = customerRepository.existsById(customerToUpdate.getId());
            if (existCustomerWithTheSameId) {
                customerRepository.saveAndFlush(customerToUpdate);
            }
        }
        else
        {
            throw new ServiceException("Customer with " + customerToUpdate.toString() + " does not exists");
        }
    }

    @Transactional
    public void deleteCustomer(int customerId)
    {
        if(customerRepository.existsCustomerById(customerId))
        {
            customersVsCouponsRepository.deleteCouponsByCustomerID(customerId);
            customerRepository.deleteCustomerById(customerId);
        }
        else
        {
            throw new ServiceException("Customer with id "+ customerId+ " does not exists");
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
