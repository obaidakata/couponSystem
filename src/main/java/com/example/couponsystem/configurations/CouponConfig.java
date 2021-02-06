package com.example.couponsystem.configurations;

import com.example.couponsystem.tables.CouponRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouponConfig
{
//    private CompanyService companyService;
//
//    @Autowired
//    public CouponConfig(CompanyService companyService)
//    {
//        this.companyService = companyService;
//    }

    @Bean
    CommandLineRunner commandLineRunnerCoupon(CouponRepository repository)
    {
        return args -> {
//            Coupon kfcCoupon1 = new Coupon(
//                    1,
//                    eCategory.Food,
//                    "5 Nagets",
//                    "Chicken Naget Description",
//                    LocalDate.of(2019, Month.MARCH, 1),
//                    LocalDate.of(2021, Month.MARCH, 20),
//                    2,
//                    60.90,
//                    "KFC_5_Nagets.png");
//
//            Coupon kfcCoupon2 = new Coupon(
//                    2,
//                    eCategory.Food,
//                    "5 Nagets",
//                    "Chicken Naget Description",
//                    LocalDate.of(2019, Month.MARCH, 1),
//                    LocalDate.of(2021, Month.MARCH, 20),
//                    2,
//                    60.90,
//                    "KFC_5_Nagets.png");
//
//            repository.saveAll(List.of(kfcCoupon1, kfcCoupon2));
        };
    }
}
