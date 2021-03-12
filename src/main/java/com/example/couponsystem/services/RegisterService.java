package com.example.couponsystem.services;

import com.example.couponsystem.customExceptions.ServiceException;
import com.example.couponsystem.tables.Customer;
import com.example.couponsystem.tables.tablesRepo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    CustomerRepository customerRepository;

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
}
