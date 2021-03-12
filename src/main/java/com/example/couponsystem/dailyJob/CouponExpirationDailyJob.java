package com.example.couponsystem.dailyJob;


import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.tablesRepo.CouponRepository;
import com.example.couponsystem.tables.tablesRepo.CustomersVsCouponsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
@Scope("singleton")
@EnableScheduling
public class CouponExpirationDailyJob
{
    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CustomersVsCouponsRepository customersVsCouponsRepository;

    @Transactional
    @Scheduled(cron = "0 0 20 * * *")
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
}