package com.example.couponsystem.company;

import com.example.couponsystem.coupon.Coupon;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.*;


@Configuration
public class CompanyConfig
{
    @Bean
    CommandLineRunner commandLineRunnerCompany(CompanyRepository repository)
    {
        return args -> {
            Company kfc = new Company(
                                    "KFC",
                                    "KFC@Gmail.com",
                                    "123"
            );

            Company mcd = new Company(
                                    "MCD",
                                    "MCD@Gmail.com",
                                    "1233"
            );
            repository.saveAll(List.of(kfc, mcd));
        };
    }
}
