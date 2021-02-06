package com.example.couponsystem.configurations;

import com.example.couponsystem.tables.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CompanyConfig
{
    @Bean
    CommandLineRunner commandLineRunnerCompany(CompanyRepository repository)
    {
        return args -> {
//            Company kfc = new Company(
//                                    "KFC",
//                                    "KFC@Gmail.com",
//                                    "123"
//            );
//
//            Company mcd = new Company(
//                                    "MCD",
//                                    "MCD@Gmail.com",
//                                    "1233"
//            );
//            repository.saveAll(List.of(kfc, mcd));
        };
    }
}
