package com.example.couponsystem.coupon;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CouponConfig
{
    @Bean
    CommandLineRunner commandLineRunnerCoupon(CouponRepository repository)
    {
        return args -> {
            Coupon kfcCoupon = new Coupon();

            Coupon mcdCoupon = new Coupon();

            repository.saveAll(List.of(kfcCoupon, mcdCoupon));
        };
    }
}
