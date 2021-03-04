package com.example.couponsystem.dailyJob;


import com.example.couponsystem.tables.tablesRepo.CouponRepository;
import com.example.couponsystem.tables.tablesRepo.CustomerRepository;
import com.example.couponsystem.tables.tablesRepo.CustomersVsCouponsRepository;
import com.example.couponsystem.utiles.Logger;
import com.example.couponsystem.enums.eClientType;
import com.example.couponsystem.loginManager.LoginManager;
import com.example.couponsystem.services.AdminService;
import com.example.couponsystem.services.CompanyService;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Component
@Scope("singleton")
public class CouponExpirationDailyJob implements CommandLineRunner
{

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CustomersVsCouponsRepository customersVsCouponsRepository;


    private Logger logger = new Logger();

    @Transactional
    public void removeAllExpiredCoupons()
    {
        LocalDate today = LocalDate.now();
        ArrayList<Coupon> expiredCoupons =  couponRepository.findCouponByEndDateBefore(today);
        if(expiredCoupons != null)
        {
            for(Coupon coupon : expiredCoupons)
            {
                customersVsCouponsRepository.deleteCustomersVsCouponsByCouponID(coupon.getId());
            }
        }

        couponRepository.deleteCouponByEndDateBefore(today);
    }

    @Override
    public void run(String... args) throws Exception
    {
        removeAllExpiredCoupons();
    }
}