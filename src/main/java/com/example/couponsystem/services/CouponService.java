package com.example.couponsystem.services;


import com.example.couponsystem.tables.Coupon;
import com.example.couponsystem.tables.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CouponService
{
    private final CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository couponRepository)
    {
        this.couponRepository = couponRepository;
    }

    public void addCoupon(Coupon coupon)
    {
        couponRepository.saveAndFlush(coupon);
    }

    public void updateCoupon(Coupon coupon)
    {
        boolean isCouponExists = couponRepository.existsById(coupon.getId());
        if(isCouponExists)
        {
            couponRepository.saveAndFlush(coupon);
        }
        else
        {
            throw new IllegalStateException("Coupon with "+ coupon.toString() + " does not exists");
        }
    }

    public void deleteCoupon(int couponId)
    {
        boolean exists = couponRepository.existsById(couponId);
        if(exists)
        {
            couponRepository.deleteById(couponId);
        }
        else
        {
            throw new IllegalStateException("Coupon with id "+ couponId + " does not exists");
        }
    }

    public ArrayList<Coupon> getAllCoupons()
    {
        return new ArrayList<>(couponRepository.findAll());
    }

    public Coupon getOneCoupon(int couponId)
    {
        return couponRepository.findCouponById(couponId);
    }

    public void addCouponPurchase(int customerId, int couponId)
    {
        //        TODO: implement
    }

    public void deleteCouponPurchase(int customerId, int couponId)
    {
        //        TODO: implement
    }

}
