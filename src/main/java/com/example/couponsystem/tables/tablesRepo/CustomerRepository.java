package com.example.couponsystem.tables.tablesRepo;

import com.example.couponsystem.tables.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
    Customer findCustomerById(int customerId);
    Customer findCustomerByEmailAndPassword(String email, String password);
    boolean existsCustomerByEmail(String email);
    boolean existsCustomerById(int customerId);
    void deleteCustomerById(int customerId);
}
