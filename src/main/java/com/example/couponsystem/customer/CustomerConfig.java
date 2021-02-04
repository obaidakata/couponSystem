package com.example.couponsystem.customer;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig
{
    @Bean
    CommandLineRunner commandLineRunnerCustomer(CustomerRepository repository)
    {
        return args -> {
            Customer obaida = new Customer();

            Customer roji = new Customer();

            repository.saveAll(List.of(obaida, roji));
        };
    }
}
