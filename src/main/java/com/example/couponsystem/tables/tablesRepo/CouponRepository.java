package com.example.couponsystem.tables.tablesRepo;

import com.example.couponsystem.tables.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer>
{
    Coupon findCouponById(int couponId);

    // TODO: 06/02/2021 implement
    ArrayList<Coupon> getCouponsById(int id);
    ArrayList<Coupon> getCouponsByCompanyID(int companyId);
//    void addCouponPurchase(int customerId, int couponId);

//    @Modifying
//    @Query("DELETE FROM CUSTOMERS_VS_COUPONS  c WHERE c.COUPON_ID=:couponId")
//    void deletePurchaseCouponByCouponID(@Param("couponId") int couponId);


}
