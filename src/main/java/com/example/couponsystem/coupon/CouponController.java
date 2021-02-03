package com.example.couponsystem.coupon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/coupon")
public class CouponController
{
    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService)
    {
        this.couponService = couponService;
    }

    @GetMapping
    public List<Coupon> getCoupons()
    {
        return couponService.getCoupons();
    }
}
