package com.example.couponsystem.tables;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>
{
    Customer findCustomerById(int customerId);
    Customer findCustomerByEmailAndPassword(String email, String password);
    boolean existsCustomerByEmail(String email);
}
