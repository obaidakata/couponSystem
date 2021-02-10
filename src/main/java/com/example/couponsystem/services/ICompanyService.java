package com.example.couponsystem.services;

import com.example.couponsystem.enums.eCategory;
import com.example.couponsystem.tables.Company;
import com.example.couponsystem.tables.Coupon;

import java.util.ArrayList;

public interface ICompanyService
{
    void addCoupon(Coupon couponToAdd);

    void updateCoupon(Coupon couponToUpdate);

    void deleteCoupon(int couponId);

    ArrayList<Coupon> getCompanyCoupons();

    ArrayList<Coupon> getCompanyCoupons(eCategory category);

    ArrayList<Coupon> getCompanyCoupons(double maxPrice);

    Company getCompanyDetails();

}
