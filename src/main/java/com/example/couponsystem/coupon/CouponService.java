package com.example.couponsystem.coupon;


import com.example.couponsystem.category.eCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@Service
public class CouponService
{
    private final CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository couponRepository)
    {
        this.couponRepository = couponRepository;
    }

    public List<Coupon> getCoupons()
    {
        return couponRepository.findAll();
    }
}
