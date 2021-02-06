//package com.example.couponsystem.controllers;
//
//import com.example.couponsystem.services.CouponService;
//import com.example.couponsystem.tables.Coupon;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping(path="api/v1/coupon")
//public class CouponController
//{
//    private final CouponService couponService;
//
//    @Autowired
//    public CouponController(CouponService couponService)
//    {
//        this.couponService = couponService;
//    }
//
//    @PostMapping
//    public void addCoupon(@RequestBody Coupon coupon)
//    {
//        couponService.addCoupon(coupon);
//    }
//
//    @PutMapping
//    public void updateCoupon(@RequestBody Coupon coupon)
//    {
//        couponService.updateCoupon(coupon);
//    }
//
//    @DeleteMapping(path = {"couponId"})
//    public void deleteCoupon(@PathVariable("couponId") int couponId)
//    {
//        couponService.deleteCoupon(couponId);
//    }
//
//    @GetMapping
//    public ArrayList<Coupon> getAllCoupons()
//    {
//        return couponService.getAllCoupons();
//    }
//
//    @GetMapping(path = {"couponId"})
//    public Coupon getOneCoupon(@PathVariable("couponId") int couponId)
//    {
//        return couponService.getOneCoupon(couponId);
//    }
//}
