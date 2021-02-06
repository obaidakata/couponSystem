package com.example.couponsystem.configurations;


import com.example.couponsystem.tables.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig
{
    @Bean
    CommandLineRunner commandLineRunnerCustomer(CustomerRepository repository)
    {
        return args -> {
//            Customer obaida = new Customer(
//                    "obaida",
//                    "kata",
//                    "obaida@gmail.com",
//                    "1234"
//            );
//
//            Customer roji = new Customer(
//                    "roji",
//                    "khory",
//                    "roji@gmail.com",
//                    "12345"
//            );
//
//            repository.saveAll(List.of(obaida, roji));
        };
    }
}
